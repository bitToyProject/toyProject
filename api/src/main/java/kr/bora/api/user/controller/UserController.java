package kr.bora.api.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Api(tags={"4. User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
@Log4j2
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @ApiOperation(value="로그인 정보", notes="로그인 정보를 보여줍니다.")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(userServiceImpl.getMyInfo());
    }

    @ApiOperation(value="사용자 정보", notes="사용자 정보를 보여줍니다.")
    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUserInfo(@ApiParam(value="username", required=true) @PathVariable String email) {
        return ResponseEntity.ok(userServiceImpl.getUserInfo(email));
    }

    @ApiOperation(value="회원 탈퇴", notes="회원을 탈퇴 합니다.")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<CommonResponse<UserResponseDto>> delete() {
        var dto = UserRequestCommand.RequestUserId.toDto();
        var response = userServiceImpl.deleteUser(dto);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value="회원 정보 변경", notes="회원정보를 변경 합니다.")
    @PutMapping("/modifyUser")
    public ResponseEntity<String> modifyUserInfo(@RequestBody UserRequestCommand.RequestPasswordChanger command) {
        var dto = command.toDto();
        userServiceImpl.modify(dto);

        return ResponseEntity.ok("회원정보 변경이 성공적으로 완료되었습니다.");
    }


}