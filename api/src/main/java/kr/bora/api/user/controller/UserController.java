package kr.bora.api.user.controller;

import kr.bora.api.common.response.CommonResponse;
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

    @DeleteMapping("/deleteUser")
    public ResponseEntity<CommonResponse<UserResponseDto>> delete() {
        var dto = UserRequestCommand.RequestUserId.toDto();
        var response = userServiceImpl.deleteUser(dto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/modifyPassword")
    public ResponseEntity<String> modifyPassword(@RequestBody UserRequestCommand.RequestPasswordChanger command) {
        var dto = command.toDto();
        userServiceImpl.modify(dto);

        return ResponseEntity.ok("비밀번호 변경이 성공적으로 완료되었습니다.");
    }
}