package kr.bora.api.mailauth.api;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Status;
import kr.bora.api.mailauth.domain.dto.AuthMailDto;
import kr.bora.api.mailauth.service.MailSendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MailController {

    private final MailSendServiceImpl mss;

    @PostMapping("/authMail")
    public ResponseEntity<String> authMail(@Valid @RequestBody AuthMailDto authMailDto) {
        mss.sendAuthMail(authMailDto);
        return ResponseEntity.ok("메일 인증이 성공되었습니다. 로그인 해주세요");
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
