package kr.bora.api.department.controller;

import java.util.List;
import kr.bora.api.department.domain.dto.DepartmentDto;
import kr.bora.api.department.domain.entity.Department;
import kr.bora.api.department.service.DepartmentService;
import kr.bora.api.user.domain.Authority;
import kr.bora.api.user.service.UserService;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<DepartmentDto> registerDepartment(@RequestBody
        DepartmentRequestCommand.RequestMakeNewDepartment command) {
        DepartmentDto departmentDto = command.toDto();
        return ResponseEntity.ok(departmentService.save(departmentDto));
    }

    @PutMapping("/modify")
    public ResponseEntity<DepartmentDto> modifyDepartment(
        // TODO : 최소 권한 설정
        @RequestBody DepartmentRequestCommand.ModifyDepartmentCommander command) {
        Long currentUserId = SecurityUtil.getCurrentUserId();

        DepartmentDto departmentDto = command.toDto();
        return ResponseEntity.ok(departmentService.modify(departmentDto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DepartmentDto>> findAllDepartment() {
        return ResponseEntity.ok(departmentService.getAllDepartmentList());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDepartment(@RequestParam long departmentId, @RequestParam long userId){
        final String responseMessage;
        if (userService.checkChangeableAuthority(userId, Department.getAuthorityGrade())) {
            departmentService.delete(departmentId);
            responseMessage = "부서를 성공적으로 삭제하였습니다.";
        } else {
            log.error("해당 기능을 사용할 수 없는 유저 requested param : {}", userId);
            responseMessage = "해당 기능을 사용할 수 없는 유저 입니다. 삭제에 실패하였습니다. 최소 관리자 권한 : " + Department.getAuthorityGrade();
        }
        return ResponseEntity.ok(responseMessage);
    }

}
