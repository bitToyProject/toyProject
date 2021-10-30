package kr.bora.api.user.controller;

import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
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

        userRequestDto.setUserId(id);
        userServiceImpl.modify(userRequestDto);

        return ResponseEntity.ok("비번 변경 성공");
    }
}