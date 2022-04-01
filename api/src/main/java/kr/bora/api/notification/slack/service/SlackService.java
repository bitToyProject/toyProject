package kr.bora.api.notification.slack.service;

import java.util.List;
import org.springframework.http.HttpStatus;

public interface SlackService {
    List<Integer> getStatus();
    void postErrorToSlack(Exception ex, Object body, HttpStatus status);
    void postErrorToSlack(Exception ex, Object body);
    void postErrorToSlack(Exception ex);
}
