package kr.bora.api.socialAuth.util;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";

    private final static String TOKEN_PREFIX = "Bearer ";

    public static String getAccessToken(HttpServletRequest request) {
        String hearderValue = request.getHeader(HEADER_AUTHORIZATION);

        if (hearderValue == null) {
            return null;
        }

        if (hearderValue.startsWith(TOKEN_PREFIX)) {
            return hearderValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
