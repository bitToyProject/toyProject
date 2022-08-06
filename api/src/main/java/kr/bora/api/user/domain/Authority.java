package kr.bora.api.user.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Authority {
    ROLE_ADMIN(1, "어드민"),
    ROLE_ASSISTANT(2, "전임 관리자"),
    ROLE_MANAGER(3,"선임 관리자"),
    ROLE_USER(4, "일반유저"),
    ROLE_GUEST(5, "게스트")
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

    public static Authority of(String symbol) {
        return Arrays.stream(Authority.values())
                .filter(r -> r.getSymbol().equals(symbol))
                .findAny()
                .orElse(ROLE_GUEST);
    }
}