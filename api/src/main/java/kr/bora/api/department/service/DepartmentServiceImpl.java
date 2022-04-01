package kr.bora.api.department.service;

import java.util.List;
import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.department.domain.entity.Department;
import kr.bora.api.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto save(final DepartmentDto dto) {
        log.info("부서 등록 시작 request dto : {} ", dto);
        Department savedEntity = departmentRepository.save(dto.toEntity());
        log.info("부서 등록 성공 registered department : {} ", savedEntity);
        return savedEntity.toDto();
    }
    @Override
    public DepartmentDto modify(final DepartmentDto dto) {
        log.info("부서 수정 시작 request dto : {} ", dto);
        Department modifiedEntity = departmentRepository.save(dto.toEntity());
        log.info("부서 수정 성공 modified department : {} ", modifiedEntity);
        return modifiedEntity.toDto();
    }
    @Override
    public List<DepartmentDto> getAllDepartmentList() {
        log.info("모든 부서 정보 조회 시작");
        List<Department> departmentList = departmentRepository.findAll();
        log.info("조회된 모든 부서 정보 : {} ",departmentList);
        return Department.toDtoList(departmentList);
    }

    @Override
    public void delete(long departmentId) {
        log.info("부서 삭제 시작");
        departmentRepository.deleteById(departmentId);
        log.info("부서 삭제 성공");
    }


}
