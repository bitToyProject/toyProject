package kr.bora.api.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.bora.api.user.dto.AssignDepartmentDto;
import kr.bora.api.user.dto.MailTempPasswordDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.dto.UserWithDepartmentDto;
import kr.bora.api.user.service.MailTempPwdService;
import kr.bora.api.user.service.UserService;
import kr.bora.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Api(tags = {"4. User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserService userService;

    private final MailTempPwdService mailTempPwdService;

    @ApiOperation(value = "로그인 정보", notes = "로그인 정보를 보여줍니다.")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴 합니다.")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> delete() {
        var dto = UserRequestCommand.RequestUserId.toDto();
        var response = userService.deleteUser(dto);

        return ResponseEntity.ok("회원 탈퇴가 성공적으로 완료되었습니다. 그 동안 이용해주셔서 감사합니다.");
    }

    @ApiOperation(value = "회원 정보 변경", notes = "회원정보를 변경 합니다.")
    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUserInfo(@RequestBody UserRequestCommand.RequestPasswordChanger command) {
        var dto = command.toDto();
        userService.modify(dto);

        return ResponseEntity.ok("회원정보 변경이 성공적으로 완료되었습니다.");
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
        MailTempPasswordDto mailTempPasswordDto = mailTempPwdService.createMail(tempPassword, username);
        mailTempPwdService.sendMail(mailTempPasswordDto);

        return ResponseEntity.ok("임시 비번 발급 성공");
    }
}