package kr.bora.api.mailauth;

import kr.bora.api.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
public class MailController {
  @Autowired
  private MailSendService mss;

//  @PostMapping("/check")
//  public ResponseEntity<CommonResponse> sendCheckMail(@Valid @RequestBody AuthMailDto authMailDto){
//    return ResponseEntity.ok(mss.sendAuthMail(authMailDto));
//  }

}
