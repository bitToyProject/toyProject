package kr.bora.api.socialAuth.domain.info;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");


    @Override
    public String getId() {
        if (responseNull()) return null;

        return (String) response.get("id");
    }

    @Override
    public String getName() {

        if (responseNull()) return null;

        return (String) response.get("nickname");
    }

    @Override
    public String getEmail() {

        if (responseNull()) return null;

        return (String) response.get("email");
    }

    @Override
    public String getImageUrl() {

        if (responseNull()) return null;

        return (String) response.get("profile_image");
    }

    @Override
    public String getGender() {
        if (responseNull()) return null;

        return (String) attributes.get("gender");
    }

    private boolean responseNull() {
        if (response == null) {
            return true;
        }
        return false;
    }
}