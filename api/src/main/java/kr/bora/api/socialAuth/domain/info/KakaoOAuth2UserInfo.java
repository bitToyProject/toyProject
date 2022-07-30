package kr.bora.api.socialAuth.domain.info;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        if (properties == null) {
            return null;
        }

        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        System.out.println("attributes = " + attributes.get("kakao_account"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account"); // 카카오 계정정보 모음
        return (String) kakaoAccount.get("email"); // 계정정보에서 email 리턴

    }

    @Override
    public String getImageUrl() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        if (properties == null) {
            return null;
        }

        return (String) properties.get("thumbnail_image");
    }
}