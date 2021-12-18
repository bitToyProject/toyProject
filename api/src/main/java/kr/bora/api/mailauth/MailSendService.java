package kr.bora.api.mailauth;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.MessagingException;

import kr.bora.api.user.dto.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class MailSendService{

  private final MailAuthRepository repository;
  private final JavaMailSender mailSender;

//  private String getAuthCode(){
//    Random random = new Random();
//    StringBuffer buffer = new StringBuffer();
//    for(int i = 0; i <3; i++){
//      int index= random.nextInt(25)+65;
//      buffer.append((char) index);
//    }
//    int num = (int) (Math.random()*10000-1);
//    buffer.append(num);
//    return buffer.toString();
//  }
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void sendAuthMail(AuthMailDto authMailDto){
//    String authKey = getAuthCode();
    log.info(authMailDto.getKey());
    try {
      MailUtil sendMail = new MailUtil(mailSender);
      sendMail.setSubject("회원가입 이메일 인증");
      sendMail.setText(new StringBuffer().append("<h1>['BORA'이메일 인증]</h1>")
          .append("<p>아래 문자를 입력하시면 이메일 인증이 완료됩니다.</p>")
//          .append("<a href='http://localhost:8080/mail/mailCheckConfirm")
//          .append(authMailDto.getAuthMail())
//          .append("&authKey=")
          .append(authMailDto.getKey())
//          .append("'target='_blenk'>이메일 인증 확인</a>")
          .toString());
//      sendMail.setFrom("wkdgothf321@gmail.com", "관리자");
      sendMail.setTo(authMailDto.getAuthMail());
      sendMail.send();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    AuthMail authMail = authMailDto.toAuthMail(authMailDto.getAuthMail(), authMailDto.getKey());
    repository.save(authMail);
  }

  public boolean checkMailAuthKey(AuthMailDto authMailDto) {
    log.info(authMailDto.getAuthMail());

    if(authMailDto.getKey().equals(repository.findAuthMailKeyByAuthMail(authMailDto.getAuthMail()))){
      repository.updateMailCheckStatus(authMailDto.getAuthMail());
      return true;
    }


    return false;
  }
}
