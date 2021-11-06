package kr.bora.api.mailauth;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

  private JavaMailSenderImpl mailSender;
  private int size;

  private String getKey(int size){
    this.size = size;
    return getAuthCode();
  }
  private String getAuthCode(){
    int num = (int) (Math.random()*10-1);

    StringBuffer buffer = new StringBuffer();
    while(buffer.length() < size){
      buffer.append(num);
    }
    return buffer.toString();
  }

  public String sendAuthMail(String email){
    String authKey = getKey(6);
    try {
      MailUtil sendMail = new MailUtil(mailSender);
      sendMail.setSubject("회원가입 이메일 인증");
      sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
          .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
          .append("<a href='http://localhost:8080/member/signUpConfirm?email=")
          .append(email)
          .append("&authKey=")
          .append(authKey)
          .append("' target='_blenk'>이메일 인증 확인</a>")
          .toString());
      sendMail.setFrom("이메일 주소", "관리자");
      sendMail.setTo(email);
      sendMail.send();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return authKey;
  }

}