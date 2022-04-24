package kr.bora.api.user.dto;

import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AssignDepartmentDto {

    private Long id;
    private DepartmentDto departmentDto;

    public User toUser() {
        DepartmentDto department = new DepartmentDto();

        return User.builder()
                .userId(id)
//                .department(departmentDto.toEntity())
                .build();
    }
}
