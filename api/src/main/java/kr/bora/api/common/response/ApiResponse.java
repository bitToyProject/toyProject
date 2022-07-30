package kr.bora.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiResponse <T>{

    private final static int SUCCESS = 200;
    private final static int NOT_FOUND = 404;
    private final static int FAILED = 500;

    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";

    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";

    private final ApiResponseHeader header;
    private final Map<String, T> body;

    public static <T> ApiResponse<T> success(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new ApiResponse<T>(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }

    public static <T> ApiResponse<T> fail() {
        return new ApiResponse<T>(new ApiResponseHeader(FAILED, FAILED_MESSAGE), null);
    }
}
