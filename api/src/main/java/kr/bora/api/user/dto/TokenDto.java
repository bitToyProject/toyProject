package kr.bora.api.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {

    private String userName;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Getter
    @NoArgsConstructor
    public static class TokenRequest{
        private String accessToken;
        private String refreshToken;
    }

}