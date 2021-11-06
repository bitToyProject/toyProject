package kr.bora.api.mailauth;

import kr.bora.api.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/mail")
public class MailController {
  @Autowired
  private MailSendService mss;

  @PostMapping("/check")
  public String sendCheckMail(@Valid @RequestParam String email){
    return mss.sendAuthMail(email);
  }

}
