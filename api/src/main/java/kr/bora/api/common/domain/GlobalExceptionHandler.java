package kr.bora.api.common.domain;

import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BoraException.class)
    public ResponseEntity<ErrorResponse> handleException(BoraException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .status(e.getCode().getStatus())
                .build();
        log.error(errorResponse.toString());
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
}
