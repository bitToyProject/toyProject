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
public class ClientErrorNotification implements SlackService{
    private final SlackConfig slackConfig;

    @Override
    public List<Integer> getStatus() {
        return Collections.singletonList(HttpStatus.Series.CLIENT_ERROR.value());
    }

    @Override
    public void postErrorToSlack(Exception ex, Object body, HttpStatus status) {
        try{
            MethodsClient methods = Slack.getInstance().methods(slackConfig.getToken());
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(slackConfig.getClient())
                .blocks(asBlocks(section(s -> s.text(
                    plainText("ERROR-STATUS : " + status + " \r\n MESSAGE : " + ex.getMessage()
                        + " \r\nCAUSE : " + body.toString())))))
//                .attachments(asAttachments(
//                    attachment(a->a
//                        .color("#ff0000")
//                        .fallback("REQUEST FAILED BECAUSE OF : " + ex.getMessage())
//                        .pretext("STATUS : " + status)
//                        .blocks(asBlocks(
//                            section(s -> s.text(plainText("BODY : " +body)))
//                        ))
//                )))
                .build();

            ChatPostMessageResponse chatPostMessageResponse = methods.chatPostMessage(request);
            log.info("Client error send notification to slack" + chatPostMessageResponse.toString());

        } catch (SlackApiException | IOException e) {
            log.error("fail to send  to slack error about error : {} ",e.getMessage(),e);
        }
    }

    @Override
    public void postErrorToSlack(Exception ex, Object body) {

    }

    @Override
    public void postErrorToSlack(Exception ex) {

    }
}
