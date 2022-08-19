package kr.bora.api.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "C001", "DUPLICATE_USER_NAME"),
    ALREADY_LIKE_CANCEL(HttpStatus.CONFLICT, "C002", "ALREADY_LIKE_CANCEL"),
    FAIL_SAVE_FILE(HttpStatus.CONFLICT, "C003", "FAIL_SAVE_FILE"),

    NOT_IMG_EXTENSION(HttpStatus.CONFLICT, "C004", "NOT_IMG_EXTENSION"),

    NOT_EXIST_ALRAM(HttpStatus.CONFLICT, "C005", "NOT_EXIST_ALARM"),
    EXIST_DUP_TEAM(HttpStatus.CONFLICT, "C006", "EXIST_DUP_TEAM"),
    EXIST_DUP_TEAM_USER(HttpStatus.CONFLICT, "C007", "EXIST_DUP_TEAM_USER");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
