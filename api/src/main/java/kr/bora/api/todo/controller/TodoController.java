package kr.bora.api.todo.controller;

//import kr.bora.api.common.response.CommonResponse;
//import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.PageRequestDto;
import kr.bora.api.todo.dto.PageResultDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoService;
import kr.bora.api.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;

    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<TodoDto, Object[]>> todoList(PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(service.getList(pageRequestDto));
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> todoSave(@RequestBody TodoRequestCommand.TodoRequest todoDto) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("Save Success Todo", service.save(todoDto.toDto()));
        return ResponseEntity.ok(obj);
    }

    @GetMapping("/read/{todoId}")
    public ResponseEntity<TodoDto> todoRead(@PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.get(todoId));
    }

    @PutMapping("/modify/{todoId}")
    public ResponseEntity<String> todoModify(@PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {

        service.modify(todoId, todoDto);

        return ResponseEntity.ok(todoId + "번 TODO가 수정되었습니다.");
    }

    @DeleteMapping("/remove/{todoId}")
    public ResponseEntity<Map<String, Object>> todoRemove(@PathVariable("todoId") Long todoId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", todoId + " 번 Todo 삭제");
        service.todoRemove(todoId);

        return ResponseEntity.ok(resultMap);
    }

}
