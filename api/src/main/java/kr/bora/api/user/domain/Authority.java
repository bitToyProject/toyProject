package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_ADMIN("1", "어드민"),
    ROLE_USER("2", "일반유저"),
    ROLE_ASSISTANT("3", "전임 관리자"),
    ROLE_MANAGER("4","선임 관리자")
    ;

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    private final String code;
    private final String symbol;

    Authority(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }
}