package kr.bora.api.mailauth.domain.dto;

import kr.bora.api.mailauth.domain.entity.AuthMail;
import lombok.*;

@RequiredArgsConstructor
@Getter
public class AuthMailDto {

    private String authMail;
    private String key;
    @Builder
    public AuthMailDto(String authMail,String key){
        this.authMail = authMail;
        this.key = key;
    }

    public AuthMail toAuthMail(String authMail, String key){
        return AuthMail.builder()
                .authMail(authMail)
                .authMailKey(key)
                .authStatus(AuthMail.AuthStatus.UNCHECKED)
                .build();
    }
}
