package kr.bora.api.subtask.controller;

import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/subtasks")
public class SubTaskController {

    private final SubTaskService service;

    @PostMapping("/save/{todoId}")
    public ResponseEntity<Map<String, Object>> subTaskSave(@RequestBody SubTaskRequestCommand.SubTaskRequest subTaskDto, @PathVariable Long todoId) {

        Map<String, Object> result = new HashMap<>();
        result.put("Save Success SubTask", subTaskDto.toDto(todoId).getTodoId() + "번 Todo의 Subtask가 등록되었습니다.");
        service.save(subTaskDto.toDto(todoId), todoId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<SubTaskDto>> subTaskList(@PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.getList(todoId));
    }

    @PutMapping("/modify/{subTaskId}")
    public ResponseEntity<String> subTaskModify(@PathVariable("subTaskId") Long subTaskId, @RequestBody SubTaskDto subTaskDto) {

        service.modify(subTaskId, subTaskDto);

        return ResponseEntity.ok(subTaskId + "번 SubTask가 수정되었습니다.");
    }

    @DeleteMapping("/remove/{subTaskId}")
    public ResponseEntity<String> subTaskRemove(@PathVariable("subTaskId") Long subTaskId) {
        service.remove(subTaskId);

        return ResponseEntity.ok(subTaskId + "번 SubTask가 성공적으로 삭제되었습니다.");
    }
}
