package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Avatar {
    DEFAULTMAN(1,"기본남캐")
    , LEVEL1MAN(2,"1레벨 남캐")
    , LEVEL2MAN(3,"2레벨 남캐")
    , DEFAULTWOMAN(10, "기본 여캐")
    , LEVEL1WOMAN(11,"1레벨 여캐")
    , LEVEL2WOMAN(12,"2레벨 여캐");

    private final int avatarNum;
    private final String desc;


    Avatar(int avatarNum, String desc) {
        this.avatarNum = avatarNum;
        this.desc = desc;
    }
}
