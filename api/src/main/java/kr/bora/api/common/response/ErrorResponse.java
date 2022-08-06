package kr.bora.api.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String code;
    private String message;
}
