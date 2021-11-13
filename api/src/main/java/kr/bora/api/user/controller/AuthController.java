package kr.bora.api.user.controller;

import kr.bora.api.common.aop.LogExecutionTime;
import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.mailauth.MailSendService;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.service.AuthService;
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

    private final AuthService authService;
    private final MailSendService mailService;

    @LogExecutionTime
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(CommonResponse.success(authService.signup(userRequestDto)));
    }
    @LogExecutionTime
    @PostMapping("/login")

    public ResponseEntity<CommonResponse> login(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.login(userRequestDto));

    }
    @LogExecutionTime
    @PostMapping("/reissue")
    public ResponseEntity<CommonResponse> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(CommonResponse.success(authService.reIssue(tokenRequestDto)));
    }


}