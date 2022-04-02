package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN(1, "어드민"),
    ROLE_ASSISTANT(2, "전임 관리자"),
    ROLE_MANAGER(3,"선임 관리자"),
    ROLE_USER(4, "일반유저")
    ;

    public int getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    private final int code;
    private final String symbol;

    Authority(int code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
}