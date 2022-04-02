package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN(1, "어드민"),
    ROLE_USER(2, "일반유저"),
    ROLE_ASSISTANT(3, "전임 관리자"),
    ROLE_MANAGER(4,"선임 관리자")
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