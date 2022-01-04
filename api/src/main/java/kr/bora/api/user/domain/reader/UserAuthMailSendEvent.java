package kr.bora.api.user.domain.reader;

import kr.bora.api.mailauth.AuthMailDto;
import kr.bora.api.mailauth.MailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserAuthMailSendEvent {

    private final MailSendService mailSendService;

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
