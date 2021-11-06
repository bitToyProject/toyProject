package kr.bora.api.user.controller;

import kr.bora.api.user.dto.TokenRequestDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("넘어온 데이터 :::::username ={}, firstname={} ",userRequestDto.getUsername(),userRequestDto.getFirstName());
        authService.signup(userRequestDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserRequestDto userRequestDto) {
        authService.login(userRequestDto);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping("/reissue")
    public ResponseEntity eissue(@RequestBody TokenRequestDto tokenRequestDto) {
        authService.reIssue(tokenRequestDto);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }


}