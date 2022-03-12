package kr.bora.api.user.controller;

import com.mysema.commons.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.mailauth.service.MailSendServiceImpl;
import kr.bora.api.user.dto.TokenDto;
import kr.bora.api.user.dto.LoginRequestDto;
import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Api(tags={"1. Auth"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins ="*")
@Log4j2
public class AuthController {

    private final AuthServiceImpl authService;
    private final MailSendServiceImpl mailService;


    @ApiOperation(value="회원 가입", notes="회원을 등록합니다.")
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signup(userRequestDto));
    }

    @ApiOperation(value="로그인", notes="로그인을 진행합니다.")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        boolean dup = mailService.isCheckedAuthMail(loginRequestDto.getUsername());
        Assert.isTrue(dup,"mail's confirmed");
        return ResponseEntity.ok(authService.login(loginRequestDto));

    }

    @ApiOperation(value="토큰 재발급", notes="토큰을 재발급 합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reIssue(tokenRequestDto));
    }

    @ApiOperation(value="username 중복체크", notes="username이 중복인지 체크합니다.")
    @GetMapping("/check/{username}")
    public ResponseEntity<Boolean> checkUserRequestDto(@ApiParam(value="username", required=true) @PathVariable String username) {
        return ResponseEntity.ok(authService.checkUsername(username));
    }


}