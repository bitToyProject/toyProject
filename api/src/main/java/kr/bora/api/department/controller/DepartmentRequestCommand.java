package kr.bora.api.department.controller;

import kr.bora.api.department.domain.dto.DepartmentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DepartmentRequestCommand {

    @Getter
    @NoArgsConstructor
    static class RequestMakeNewDepartment{
        private String depatmentName;
        private String desc;
        private Long userId;
        DepartmentDto toDto(){
            return DepartmentDto.builder()
                .departmentName(depatmentName)
                .build();
        }
    }
    @Getter
    @NoArgsConstructor
    public class ModifyDepartmentCommander {
        private long departmentId;
        private String departmentName;
        private String departmentSubName;
        private String description;
        DepartmentDto toDto(){
            return DepartmentDto.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .departmentSubName(departmentSubName)
                .departmentDescription(description)
                .build();
        }

    }
}
