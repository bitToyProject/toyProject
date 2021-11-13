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


@Service
@Slf4j
public class MailSendService{

  @Autowired
  private MailAuthRepository repository;
  @Autowired
  private JavaMailSender mailSender;

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

  public void sendAuthMail(AuthMailDto authMailDto){
    String authKey = getAuthCode();
    log.info(authKey);
    try {
      MailUtil sendMail = new MailUtil(mailSender);
      sendMail.setSubject("회원가입 이메일 인증");
      sendMail.setText(new StringBuffer().append("<h1>['BORA'이메일 인증]</h1>")
          .append("<p>아래 문자를 입력하시면 이메일 인증이 완료됩니다.</p>")
//          .append("<a href='http://localhost:8080/mail/mailCheckConfirm")
          .append(authMailDto.getAuthMail())
          .append("&authKey=")
          .append(authKey)
          .append("' target='_blenk'>이메일 인증 확인</a>")
          .toString());
      sendMail.setFrom("wkdgothf321@gmail.com", "관리자");
      sendMail.setTo(authMailDto.getAuthMail());
      sendMail.send();
    } catch (MessagingException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    AuthMail authMail = authMailDto.toAuthMail(authMailDto.getAuthMail(),authKey);
    repository.save(authMail);
  }

  public boolean checkMailAuthKey(AuthMailDto authMailDto) {
    log.info(authMailDto.getAuthMail());
    log.info("auth main query  == {}", repository.findAuthMailKeyByAuthMail(authMailDto.getAuthMail()));
    if(authMailDto.getKey().equals(repository.findAuthMailKeyByAuthMail(authMailDto.getAuthMail())))
      return true;
    return false;
  }
}
