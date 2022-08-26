package kr.bora.api.user.controller;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.domain.Avatar;
import kr.bora.api.user.dto.AssignDepartmentDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;

public class UserRequestCommand {


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
