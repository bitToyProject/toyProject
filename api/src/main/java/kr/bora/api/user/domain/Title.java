package kr.bora.api.user.domain;

import lombok.Getter;

@Getter
public enum Title {
    STARTER("초보자"),
    BEGINNER("견습자"),
    PERFORMER("수행자"),
    HUNTER("헌터"),
    RAINGER("레인저"),
    MASTER("마스터");
    private final String titleName;
    Title(final String titleName) {
        this.titleName = titleName;
    }

}
