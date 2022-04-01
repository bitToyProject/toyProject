package kr.bora.api.notification.slack.service;

import static com.slack.api.model.block.Blocks.asBlocks;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import kr.bora.api.config.SlackConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public void postErrorToSlack(Exception ex, Object body, HttpStatus status) {
        try{
            MethodsClient methods = Slack.getInstance().methods(slackConfig.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(slackConfig.getServer())
                .blocks(asBlocks(section(s -> s.text(
                    plainText("ERROR-STATUS : " + status + " \r\n MESSAGE : " + ex.getMessage()
                        + " \r\nCAUSE : " + body.toString())))))
                .build();
            ChatPostMessageResponse chatPostMessageResponse = methods.chatPostMessage(request);
            log.info("Server error send notification to slack" + chatPostMessageResponse.toString());
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void postErrorToSlack(Exception ex, Object body) {

    }

    @Override
    public void postErrorToSlack(Exception ex) {

    }
}
