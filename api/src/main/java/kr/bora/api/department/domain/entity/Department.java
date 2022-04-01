package kr.bora.api.department.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import kr.bora.api.common.util.ObjUtils;
import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.user.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Getter
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name")
    private String name;

    @Column(name = "department_sub_name")
    private String subName;

    @Column(name = "department_desc")
    private String description;

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Transient
    private static String authorityGrade = ObjUtils.getSafeString(Authority.ROLE_ADMIN);

    public static String getAuthorityGrade(){
        return authorityGrade;
    }

    public DepartmentDto toDto() {
        return DepartmentDto.builder()
            .departmentId(id)
            .departmentName(name)
            .departmentSubName(subName)
            .departmentDescription(description)
            .build();
    }
    public static List<DepartmentDto> toDtoList(List<Department> dtoList){
        List<DepartmentDto> mappedDtoList = new ArrayList<>();
        for (Department dto : dtoList) {
            mappedDtoList.add(dto.toDto());
        }
        return mappedDtoList;
    }
}
