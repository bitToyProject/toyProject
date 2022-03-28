package kr.bora.api.notification.slack.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import kr.bora.api.config.SlackConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServerErrorNotification implements SlackService{
    private final SlackConfig slackConfig;

    @Override
    public List<Integer> getStatus() {
        return Collections.singletonList(HttpStatus.Series.SERVER_ERROR.value());
    }

    @Override
    public void postErrorToSlack(Exception ex, Object body, HttpHeaders headers, HttpStatus status) {
        try{
            MethodsClient methods = Slack.getInstance().methods(slackConfig.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(slackConfig.getServer())
                    .text("should have to do texting")
                    .build();

            methods.chatPostMessage(request);

            log.info("Server error send notification to slack");
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

}
