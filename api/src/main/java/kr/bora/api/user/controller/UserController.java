package kr.bora.api.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.bora.api.user.dto.*;
import kr.bora.api.user.service.MailTempPwdService;
import kr.bora.api.user.service.UserService;
import kr.bora.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Api(tags = {"4. User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    private final MailTempPwdService mailTempPwdService;


    @ApiOperation(value = "로그인 정보", notes = "로그인 정보를 보여줍니다.")
    @GetMapping("/me")
    public ResponseEntity<UserDto.UserResponse> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @ApiOperation(value = "회원 정보 변경", notes = "회원정보를 변경 합니다.")
    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUserInfo(@RequestBody UserDto.userInfoModify chanUserInfo) {

        UserDto.UserRequest userRequest = chanUserInfo.changeUserInfo();
        userService.modify(userRequest);

        return ResponseEntity.ok("회원정보 변경이 성공적으로 완료되었습니다.");
    }


    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴 합니다.")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser() {

        var dto = UserDto.userDelete.deleteUserId();

        userService.deleteUser(dto);

        return ResponseEntity.ok("회원 탈퇴가 성공적으로 완료되었습니다. 그 동안 이용해주셔서 감사합니다.");
    }


    @PutMapping("/assign/department")
    public ResponseEntity<UserWithDepartmentDto> assignUserDepartment(
            @RequestBody UserRequestCommand.RequestAssignDepartment command) {
        var dto = command.toDto();
        return ResponseEntity.ok(userService.departmentAssigning(dto));
    }

    @PostMapping("/temp_pwd")
    public ResponseEntity<String> sendTempPwd(@RequestParam String username) throws MessagingException, UnsupportedEncodingException {
        String tempPassword = userService.getTempPassword();

        userService.updatePassword(tempPassword, username);
        UserDto.MailTempPwdDto createMail = mailTempPwdService.createMail(tempPassword, username);
        mailTempPwdService.sendMail(createMail);

        return ResponseEntity.ok("임시 패스워드 발급 성공");
    }
}