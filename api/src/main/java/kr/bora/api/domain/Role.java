package kr.bora.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    MEMBER("ROLE_MEMBER");

    private String value;
}
