package kr.bora.api.common.domain;

import kr.bora.api.notification.slack.service.ServerErrorNotification;
import kr.bora.api.notification.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class BoraException extends ResponseEntityExceptionHandler {

    private final SlackService slackService;

    @Override
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception Notification ::::: Exception occoured");
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body, headers, status, request);

        if(status.is4xxClientError()){
            slackService.postErrorToSlack(ex, body, headers, status);
        }else if(status.is5xxServerError()){
            slackService.postErrorToSlack(ex, body, headers, status);
        }
        return objectResponseEntity;
    }

//    @ExceptionHandler(IllegalStateException.class)
//    protected ResponseEntity<Object> handleIllegalStateException(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        log.info("Exception Notification ::::: IllegalStateException occoured");
//        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body, headers, status, request);
//        if(status.is4xxClientError()){
//            slackService.postErrorToSlack(objectResponseEntity.toString());
//        }else if(status.is5xxServerError()){
//            slackService.postErrorToSlack(objectResponseEntity.toString());
//        }
//        return objectResponseEntity;
//    }
}
