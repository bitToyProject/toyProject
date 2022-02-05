package kr.bora.api.mailauth.domain.dto;

import kr.bora.api.mailauth.domain.entity.AuthMail;
import kr.bora.api.mailauth.domain.entity.AuthMail.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class AuthMailDto {

    private String authMail;
    private String key;

    public AuthMail toAuthMail(String authMail, String key){
        return AuthMail.builder()
                .authMail(authMail)
                .authMailKey(key)
                .authStatus(AuthStatus.UNCHECKED)
                .build();
    }
}
