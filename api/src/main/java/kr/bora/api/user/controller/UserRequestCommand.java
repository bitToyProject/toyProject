package kr.bora.api.user.controller;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.dto.AssignDepartmentDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;

public class UserRequestCommand {

    @Getter
    @NoArgsConstructor
    public static class RequestPasswordChanger {
        @NotNull(message = "새로운 password값이 필수 있니다.")
        private String password;
        @NotNull(message = "새로운 passwordCheck값이 필수 입니다.")
        private String checkPassword;

        private String nickname;

        public UserRequestDto toDto() {
            if (!password.equals(checkPassword)) {
                throw new InvalidParameterException("새로운 비밀번호와 비밀번호 확인 값이 같지 않습니다.");
            }
            var userId = SecurityUtil.getCurrentUserId();
            return UserRequestDto.builder()
                    .userId(userId)
                    .password(password)
                    .nickName(nickname)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestUserId {
        public static UserRequestDto toDto() {
            var userId = SecurityUtil.getCurrentUserId();
            return UserRequestDto.builder()
                    .userId(userId)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestAssignDepartment{
        @NotNull(message = "회원 아이디는 필수 사항 입니다.")
        private Long userId;
        @NotNull(message = "배정할 부서가 빠졌습니다.")
        private DepartmentDto departmentDto;
        public AssignDepartmentDto toDto(){
            return AssignDepartmentDto.builder()
                    .id(userId)
                    .departmentDto(departmentDto)
                    .build();
        }
    }

}
