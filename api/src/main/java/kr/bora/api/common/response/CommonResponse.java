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
    private T data;
    private int status;

    public static <T> CommonResponse<T> success(T data){
        return (CommonResponse<T>) CommonResponse.builder()
                .data(data)
                .status(200)
                .build();
    }
    public static <T>  CommonResponse<T> success(){
        return (CommonResponse<T>) CommonResponse.builder()
                .status(200)
                .build();
    }
    public static CommonResponse fail(Status status){
        return CommonResponse.builder()
                .status(status.getStatus())
                .build();
    }

//    public enum Result{
//        SUCCESS, FAIL
//    }
}
