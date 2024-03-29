package kr.bora.api.subtask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"3. SubTask"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/subtasks")
public class SubTaskController {

    private final SubTaskService service;

    /**
     * SubTask 등록
     *
     * @param subTaskDto
     * @param todoId
     * @return
     */
    @ApiOperation(value = "SubTask 등록", notes = "SubTask를 등록 합니다.")
    @PostMapping("/save/{todoId}")
    public ResponseEntity<ApiResponse> subTaskSave(@Valid @RequestBody SubTaskDto.Request subTaskDto, @ApiParam(value = "Todo 번호", required = true) @PathVariable Long todoId) {

        service.subTaskSave(subTaskDto, todoId);

        return ResponseEntity.ok(ApiResponse.success("response save data", subTaskDto));
    }

    /**
     * Subtask 읽기
     *
     * @param subTaskId
     * @return
     */
    @ApiOperation(value = "SubTask 단건 조회", notes = "SubTask 한 건을 조회합니다.")
    @GetMapping("/read/{subTaskId}")
    public ResponseEntity<ApiResponse> subTaskRead(@ApiParam(value = "SubTask 번호", required = true) @PathVariable Long subTaskId) {
        SubTaskDto.Response response = service.subTaskRead(subTaskId);
        return ResponseEntity.ok(ApiResponse.success("response read data", response));
    }

    /**
     * SubTask 목록
     *
     * @param todoId
     * @return
     */
    @ApiOperation(value = "SubTask 목록", notes = "SubTask 목록을 보여줍니다.")
    @GetMapping("/list/{todoId}")
    public ResponseEntity<ApiResponse> subTaskList(@ApiParam(value = "Todo 번호", required = true) @PathVariable("todoId") Long todoId) {
        List<SubTaskDto.Response> responseList = service.subTaskList(todoId);
        return ResponseEntity.ok(ApiResponse.success("response list data",  responseList));
    }

    /**
     * SubTask 수정
     *
     * @param subTaskId
     * @param subTaskDto
     * @return
     */
    @ApiOperation(value = "SubTask 변경", notes = "SubTask를 변경 합니다.")
    @PutMapping("/modify/{subTaskId}")
    public ResponseEntity<ApiResponse> subTaskModify(@ApiParam(value = "SubTask 번호", required = true) @PathVariable("subTaskId") Long subTaskId, @Valid @RequestBody SubTaskDto.Request subTaskDto) {

        service.subTaskModify(subTaskId, subTaskDto);

        return ResponseEntity.ok(ApiResponse.success("response update data", subTaskDto));
    }

    /**
     * SubTask 삭제
     *
     * @param subTaskId
     * @return
     */
    @ApiOperation(value = "SubTask 삭제", notes = "SubTask를 삭제 합니다.")
    @DeleteMapping("/remove/{subTaskId}")
    public ResponseEntity<ApiResponse> subTaskRemove(@ApiParam(value = "SubTask 번호", required = true) @PathVariable("subTaskId") Long subTaskId) {

        service.subTaskRemove(subTaskId);

        return ResponseEntity.ok(ApiResponse.success("response delete success", subTaskId + "번 SubTask가 성공적으로 삭제되었습니다."));
    }
}
