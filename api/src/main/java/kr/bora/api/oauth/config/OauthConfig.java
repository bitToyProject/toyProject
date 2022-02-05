package kr.bora.api.oauth.config;

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

    public static HttpEntity<MultiValueMap<String, String>> kakaoHttpEntity() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","5501273f1cc12271bbecc0183e5c77f4");
        params.add("redirect_uri","http://localhost:8080/oauth/kakao");
        params.add("code",
            "GhhXcHaLH0ESJgUvjYGf8bAtz9A6pLDOitRmxSIsv9wgnVWw1hRWNHdiZnHQOmK-vnn-Two9dNsAAAF-XJlP7Q");
        params.add("client_secret","nfSzoJ9ny0CxHr3z0qFZzSxCBvjlENF8");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
        return kakaoTokenRequest;
    }

    /**
     * POST /oauth/token HTTP/1.1
     * Host: kauth.kakao.com
     * Content-type: application/x-www-form-urlencoded;charset=utf-8
     * 이 형실으로 카카오 쪽으로 key-value post 요청
     */
    public static ResponseEntity<String> requestKakaoAccessToken() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","5501273f1cc12271bbecc0183e5c77f4");
        params.add("redirect_uri","http://localhost:8080/oauth/kakao");
        params.add("code",
            "GhhXcHaLH0ESJgUvjYGf8bAtz9A6pLDOitRmxSIsv9wgnVWw1hRWNHdiZnHQOmK-vnn-Two9dNsAAAF-XJlP7Q");
        params.add("client_secret","nfSzoJ9ny0CxHr3z0qFZzSxCBvjlENF8");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

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
