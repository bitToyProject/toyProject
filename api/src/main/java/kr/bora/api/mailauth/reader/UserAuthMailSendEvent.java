package kr.bora.api.mailauth.reader;

import kr.bora.api.mailauth.domain.dto.AuthMailDto;
import kr.bora.api.mailauth.service.MailSendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserAuthMailSendEvent {

    private final MailSendServiceImpl mailSendService;

    private String getAuthCode(){
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i <3; i++){
            int index= random.nextInt(25)+65;
            buffer.append((char) index);
        }
        int num = (int) (Math.random()*10000-1);
        buffer.append(num);
        return buffer.toString();
    }

    @EventListener
    public void AuthMailSend(MailSender userInfo){
        AuthMailDto BuildedAuthMailDto = AuthMailDto.builder()
                .authMail(userInfo.getThisData().getUsername())
                .key(getAuthCode()).build();
        mailSendService.sendAuthMail(BuildedAuthMailDto);
    }
}
