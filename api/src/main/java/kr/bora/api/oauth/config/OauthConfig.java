package kr.bora.api.oauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
@Component
public class OauthConfig {
    @Value("${oauth.granttype}")
    private String grantType;

    @Value("${oauth.clientid}")
    private String clientId;

    @Value("${oauth.redirecturi}")
    private String redirectUri;

    @Value("${oauth.code}")
    private String code;

    @Value("${oauth.clientsecret}")
    private String clientsecret;

    @Value("${oauth.headervalue")
    private String headerValue;

    public HttpEntity<MultiValueMap<String, String>> kakaoHttpEntity() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id",clientId);
        params.add("redirect_uri",redirectUri);
        params.add("code", code);
        params.add("client_secret",clientsecret);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type",headerValue);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
        return kakaoTokenRequest;
    }

    /**
     * POST /oauth/token HTTP/1.1
     * Host: kauth.kakao.com
     * Content-type: application/x-www-form-urlencoded;charset=utf-8
     * 이 형실으로 카카오 쪽으로 key-value post 요청
     */
    public ResponseEntity<String> requestKakaoAccessToken() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type",grantType);
        params.add("client_id",clientId);
        params.add("redirect_uri",redirectUri);
        params.add("code", code);
        params.add("client_secret",clientsecret);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type",headerValue);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );
    }
}
