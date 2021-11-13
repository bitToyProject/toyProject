package kr.bora.api.subtask.controller;

import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.service.SubTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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

    private final SubTaskServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> subTaskSave(SubTaskDto subTaskDto) {

        Map<String, Long> result = new HashMap<>();
        result.put("result", service.save(subTaskDto));

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<SubTaskDto>> subTaskList(@PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.getList(todoId));
    }

    @PutMapping("/modify/{subTaskId}")
    public ResponseEntity<String> subTaskModify(SubTaskDto subTaskDto) {

        service.modify(subTaskDto);

        return ResponseEntity.ok(subTaskDto.getSubTaskId() + "번이 수정되었습니다.");
    }

    @DeleteMapping("/remove/{subTaskId}")
    public ResponseEntity<String> subTaskRemove(@PathVariable("subTaskId") Long subTaskId) {
        service.remove(subTaskId);

        return ResponseEntity.ok("SubTask가 성공적으로 삭제되었습니다.");
    }
}
