package kr.bora.api.common.domain;

import kr.bora.api.notification.slack.factory.SlackFactory;
import kr.bora.api.notification.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class BoraExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception Notification ::::: Exception occoured");
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body,
            headers, status, request);
        SlackService slackService = SlackFactory.getSeries(status);
        slackService.postErrorToSlack(ex, body, status);
        return objectResponseEntity;
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<Object> handleIllegalStateException(Exception ex, Object body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Exception Notification ::::: IllegalStateException occoured");
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body,
            headers, status, request);
        SlackService slackService = SlackFactory.getSeries(status);
        slackService.postErrorToSlack(ex, body, status);
        return objectResponseEntity;
    }


//    @ExceptionHandler(ReflectiveOperationException.class)
//    protected ResponseEntity<Object> handleReflectiveOperationException() {
//        log.info("Exception Notification ::::: IllegalStateException occoured");
//        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body, status, request);
//        SlackService slackService = SlackFactory.getSeries(status);
//        slackService.postErrorToSlack(ex, body, status);
//        return objectResponseEntity;
//    }
}
