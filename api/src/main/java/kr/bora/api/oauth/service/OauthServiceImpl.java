package kr.bora.api.oauth.service;

import kr.bora.api.oauth.config.OauthConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RequiredArgsConstructor
@Service
@Slf4j
public class OauthServiceImpl implements OauthService{
    private final String GRANT_TYPE = "authorization_code";
    private final String CLIENT_ID = "5501273f1cc12271bbecc0183e5c77f4";
    private final String REDIRECT_URL = "http://localhost:8080/oauth/kakao";
    private final String CLIENT_SECRET = "nfSzoJ9ny0CxHr3z0qFZzSxCBvjlENF8";
    private final String TOKEN_URL =  "https://kauth.kakao.com/oauth/token";
    private final String AUTH_URL = "https://kauth.kakao.com/oauth/authorize";


    @Override
    public String getKakaoAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(TOKEN_URL)
                .queryParam("grant_type", GRANT_TYPE)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URL)
                .queryParam("code", code)
                .queryParam("client_secret", CLIENT_SECRET);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.POST,
                request,
                String.class
        );
        if(responseEntity.getStatusCode() == HttpStatus.OK){
            return responseEntity.getBody();
        }
        return "error";
    }

    @Override
    public String getAuthCode() {
//        https://kauth.kakao.com/oauth/authorize?client_id={위에서 받은 restapi키}&redirect_uri=http://localhost:8080/oauth/kakao&response_type=code
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(AUTH_URL)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URL)
                .queryParam("response_type","code");
//                .queryParam("client_secret", CLIENT_SECRET);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                request,
                String.class
        );
        return responseEntity.getBody();
    }
}
