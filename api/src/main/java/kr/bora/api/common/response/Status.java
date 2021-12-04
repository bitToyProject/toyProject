package kr.bora.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    PARAMETER_ERROR(400),
    NOAUTH_ERROR(401),
    NEED_PAYMENT(402),
    SERVER_SET_FORBIDDEN(403),
    URL_ERROR(404),
    NOTALLOW_HTTPMETHOD(405),
    CONTENTS_NOT_ALLOW(406),
    NEED_TO_AUTH_PROXY(407),
    REQUIRE_TIMEOUT(408),
    CONFLICT(409),
    JPACONDUCTERROR(600)

    ,UNMATCHED_USER(400),
    UNUSABLE_REFRESH_TOKEN(400);

    private final int status;

    public int getErrorMsg() {
        return status;
    }
}
