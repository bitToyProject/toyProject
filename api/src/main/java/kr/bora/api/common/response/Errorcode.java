package kr.bora.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Errorcode {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시후 다시 시도하십시오."),
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),
    ALREADY_EXIT_USER("이미 존재하는 유저입니다."),

    //토큰관련
    UNMATCHED_USER("토큰의 사용자 정보가 일치하지 않습니다."),
    UNUSABLE_REFRESH_TOKEN("Refresh Token이 유효하지 않습니다.");

    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg,arg);
    }
}
