package kr.bora.api.notification.slack.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface SlackService {
    List<Integer> getStatus();
    void postErrorToSlack(Exception ex, Object body, HttpHeaders headers, HttpStatus status);
}
