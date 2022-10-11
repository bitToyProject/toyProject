package kr.bora.api.oauth.service;

import kr.bora.api.user.jwt.TokenProvider;
import kr.bora.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class OauthService {
    private static final String NAVER_USER_INFO_URI = "https://openapi.naver.com/v1/nid/me";
    private final String GRANT_TYPE = "authorization_code";
    private final String CLIENT_ID = "hIroIYSwEyKzWByX_nQB";
    private final String REDIRECT_URL = "http://localhost:3000/oauth/redirect";
    private final String CLIENT_SECRET = "QjAgsg7mNI";
    private final String TOKEN_URL =  "https://nid.naver.com/oauth2.0/token";
    private final String AUTH_URL = "https://nid.naver.com/oauth2.0/authorize";

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    //네이버 용
    public String getRequestLoginUrl() {
       final String state = new BigInteger(130, new SecureRandom()).toString();
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add("response_type", "code");
        requestParam.add("state", state);
        requestParam.add("client_id", CLIENT_ID);
        requestParam.add("redirect_uri", REDIRECT_URL);
        requestParam.add("client_secret", CLIENT_SECRET);
        return UriComponentsBuilder.fromUriString(AUTH_URL)
                .queryParams(requestParam)
                .build().encode()
                .toString();

    }

    public ResponseEntity<?> requestAccessToken(String code, String state) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("code", code);
        requestBody.add("state", state);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("grant_type",GRANT_TYPE);

        return new RestTemplate().postForEntity(TOKEN_URL, requestBody, Map.class);
    }
    public void naverToken(String code, HttpServletResponse response) throws IOException {
        try {
            JSONParser jsonParser = new JSONParser();
            String header = "Bearer " + code;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(NAVER_USER_INFO_URI, requestHeaders);
            JSONObject parse = (JSONObject) jsonParser.parse(responseBody);

            JSONObject responseParse = (JSONObject) parse.get("response");
            String encodeUserName = (String) responseParse.get("name");
            String loginId = (String) responseParse.get("id");
            String email = (String) responseParse.get("email");
            String gender = (String) responseParse.get("gender");
            String userName = new String(encodeUserName.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAuthorizeCode(String code) {
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

    public String getUserInfoByAccessToken(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        return restTemplate.postForObject(url, request, String.class);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }



    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
