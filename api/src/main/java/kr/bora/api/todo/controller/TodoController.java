package kr.bora.api.todo.controller;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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

    private final TodoServiceImpl service;

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> todoList() {

        return ResponseEntity.ok(service.getList());
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> todoSave(TodoDto todoDto) {

        Map<String, Long> result = new HashMap<>();
        result.put("result", (service.TodoSave(todoDto)));

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/read/{todoId}")
    public ResponseEntity<TodoDto> todoRead(@PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.get(todoId));
    }

    @PostMapping("/modify/{todoId}")
    public ResponseEntity<Map<String, Object>> todoModify(TodoDto todoDto) {

        Map<String, String> result = new HashMap<>();
        result.put("result", "Todo List Success");

        service.modify(todoDto);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{todoId}")
    public ResponseEntity<String> todoRemove(@PathVariable("todoId") Long todoId) {

        service.todoRemove(todoId);

        return ResponseEntity.ok("Todo List가 삭제되었습니다.");
    }

}
