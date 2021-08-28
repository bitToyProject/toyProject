package kr.bora.api.controller;

import kr.bora.api.dto.UserRequestDto;
import kr.bora.api.dto.UserResponseDto;
import kr.bora.api.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;


    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(userServiceImpl.getMyInfo());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userServiceImpl.getUserInfo(email));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userServiceImpl.delete(id));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modify(@PathVariable("id") Long id, @RequestBody UserRequestDto userRequestDto) {

        userRequestDto.setId(id);
        userServiceImpl.modify(userRequestDto);

        return ResponseEntity.ok("비번 변경 성공");
    }
}