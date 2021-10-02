package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_USER("2","일반유저"),
    ROLE_ADMIN("1","어드민");

    private final String code;
    private final String symbol;
    Authority(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
}