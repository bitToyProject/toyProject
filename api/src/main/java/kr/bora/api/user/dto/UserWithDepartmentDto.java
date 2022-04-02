package kr.bora.api.user.dto;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserWithDepartmentDto {

    private long userId;

    private String username;

    private String nickname;

    private String firstName;

    private String lastName;

    private Integer gender;

    private String phoneNumber;

    private DepartmentDto departmentDto;

    public static UserWithDepartmentDto toDto(User user) {
        return UserWithDepartmentDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNum())
                .departmentDto(user.getDepartment().toDto())
                .build();
    }
}
