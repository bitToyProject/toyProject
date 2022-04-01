package kr.bora.api.common.domain;

import kr.bora.api.notification.slack.factory.SlackFactory;
import kr.bora.api.notification.slack.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@RestControllerAdvice
@RequiredArgsConstructor
public class BoraExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 모든 responseEntityException은 결국 아래의 메서드로 return 되게 되어 있다.
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return 슬랙 알람을 보내고 ResponseEntity를 리턴한다.
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ResponseException Notification ::::: Exception occoured");
        ResponseEntity<Object> objectResponseEntity = super.handleExceptionInternal(ex, body,
            headers, status, request);
        SlackService slackService = SlackFactory.getSeries(status);
        slackService.postErrorToSlack(ex, body, status);
        return objectResponseEntity;
    }
}
