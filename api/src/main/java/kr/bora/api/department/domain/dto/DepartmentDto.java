package kr.bora.api.department.domain.dto;

import kr.bora.api.common.util.ObjUtils;
import kr.bora.api.department.domain.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private String departmentSubName;
    private String departmentDescription;


    public Department toEntity() {
        return Department.builder()
            .id(departmentId)
            .name(ObjUtils.getSafeString(departmentName))
            .subName(ObjUtils.getSafeString(departmentSubName))
            .description(ObjUtils.getSafeString(departmentDescription))
            .build();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
