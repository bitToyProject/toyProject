package kr.bora.api.notification.slack.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ClientErrorNotification implements SlackService{


    @Override
    public List<Integer> getStatus() {
        return Collections.singletonList(HttpStatus.Series.CLIENT_ERROR.value());
    }

    @Override
    public void postErrorToSlack(Exception ex, Object body, HttpHeaders headers, HttpStatus status) {

    }

}
