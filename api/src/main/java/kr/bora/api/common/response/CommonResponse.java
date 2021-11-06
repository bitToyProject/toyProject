package kr.bora.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T>{
//    private Result result;
    private T data;
//    private String message;
    private int status;

    public static <T> CommonResponse<T> success(T data){
        return (CommonResponse<T>) CommonResponse.builder()
//                .result(Result.SUCCESS)
                .data(data)
                .status(200)
                .build();
    }
    public static CommonResponse fail(Status status){
        return CommonResponse.builder()
//                .result(Result.FAIL)
//                .message(errorcode.getErrorMsg())
                .status(status.getStatus())
                .build();
    }

//    public enum Result{
//        SUCCESS, FAIL
//    }
}
