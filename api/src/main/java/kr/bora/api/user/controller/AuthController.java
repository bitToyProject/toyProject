package kr.bora.api.user.controller;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.mailauth.service.MailSendServiceImpl;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
@Log4j2
public class AuthController {

    private final AuthServiceImpl authService;
    private final MailSendServiceImpl mailService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(CommonResponse.success(authService.signup(userRequestDto)));
    }
    @PostMapping("/login")

    public ResponseEntity<CommonResponse> login(@RequestBody UserRequestDto userRequestDto) {

        return ResponseEntity.ok(authService.login(userRequestDto));

    }
    @PostMapping("/reissue")
    public ResponseEntity<CommonResponse> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(CommonResponse.success(authService.reIssue(tokenRequestDto)));
    }

    // 중복체크
    @GetMapping("/check/{username}")
    public ResponseEntity<Boolean> checkUserRequestDto(@PathVariable String username) {
        return ResponseEntity.ok(authService.checkUsername(username));
    }


}