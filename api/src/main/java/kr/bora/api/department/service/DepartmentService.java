package kr.bora.api.department.service;

import java.util.List;
import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.department.domain.entity.Department;

public interface DepartmentService {

    DepartmentDto save(DepartmentDto dto);

    DepartmentDto modify(DepartmentDto dto);

    List<DepartmentDto> getAllDepartmentList();

    void delete(long departmentId);
}
