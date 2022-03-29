package kr.bora.api.notification.slack.factory;

import kr.bora.api.notification.slack.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
@Slf4j
@Service
public class SlackFactory {
    private static final HashMap<Integer, SlackService> slackService = new HashMap<>();
    private final List<SlackService> serviceList;


    public SlackFactory(List<SlackService> serviceList) {
        this.serviceList = serviceList;
    }
    @PostConstruct
    public void initSlackFactory(){
        log.info("slack notify concreat");
        for (SlackService service : serviceList) {
            for (int statusSeriesCode : service.getStatus()) {
                slackService.put(statusSeriesCode, service);
            }
        }
    }
    public static SlackService getSeries(HttpStatus httpStatus){
        return slackService.get(httpStatus.series().value());
    }
}
