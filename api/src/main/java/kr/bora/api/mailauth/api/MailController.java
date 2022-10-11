package kr.bora.api.mailauth.api;

import kr.bora.api.common.response.ApiResponse;
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
public class MailController {

    private final MailSendServiceImpl mss;

    @PostMapping("/authMail")
    public ResponseEntity<String> authMail(@Valid @RequestBody AuthMailDto authMailDto) {
        mss.sendAuthMail(authMailDto);
        return ResponseEntity.ok("메일 인증이 성공되었습니다. 로그인 해주세요");
    }

    @GetMapping("/check")
    public ResponseEntity<ApiResponse> checkAuthMail(String username, String authKey) {

        if (mss.checkMailAuthKey(username, authKey)) {
            return ResponseEntity.ok(ApiResponse.success(username+" 인증이 완료되었습니다.", "로그인 해주세요." ));
        } else {
            return ResponseEntity.ok(ApiResponse.fail());
        }
    }
}
