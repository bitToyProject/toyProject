package kr.bora.api.mailauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthMailDto {

    private String authMail;
    private String key;

    @Builder
    public AuthMail toAuthMail(String authMail, String key){
        return AuthMail.builder()
                .authMail(authMail)
                .authMailKey(key)
                .build();
    }
}
