package kr.bora.api.mailauth;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Status;
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

  @PostMapping("/authMail")
  public ResponseEntity<CommonResponse> authMail(@Valid @RequestBody AuthMailDto authMailDto){
    mss.sendAuthMail(authMailDto);
    return ResponseEntity.ok(CommonResponse.success());
  }

  @PostMapping("/check")
  public ResponseEntity<CommonResponse> checkAuthMail(@Valid @RequestBody AuthMailDto authMailDto) {
    if (mss.checkMailAuthKey(authMailDto)) {
      return ResponseEntity.ok(CommonResponse.success());
    } else {
      return ResponseEntity.ok(CommonResponse.fail(Status.PARAMETER_ERROR));
    }
  }
}
