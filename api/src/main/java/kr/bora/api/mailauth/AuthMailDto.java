package kr.bora.api.mailauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthMailDto {

    private String authMailKey;

    @Builder
    public AuthMailDto(String authMailKey){
        this.authMailKey = authMailKey;
    }
    public static AuthMail toAuthMail(String authMailKey){
        return new AuthMail(authMailKey);
    }
}
