package kr.bora.api.todo.controller;

import kr.bora.api.common.response.CommonResponse;
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

//    @GetMapping("/list")
//    public ResponseEntity<List<Todo>> todoList() {
//
//        return ResponseEntity.ok(service.getList());
//    }

    @PostMapping("/save")
    public ResponseEntity<CommonResponse<TodoDto>> todoSave(@RequestBody TodoDto todoDto) {


        return ResponseEntity.ok(service.TodoSave(todoDto));
    }

//    @GetMapping("/read/{todoId}")
//    public ResponseEntity<TodoDto> todoRead(@PathVariable("todoId") Long todoId) {
//
//        return ResponseEntity.ok(service.get(todoId));
//    }

//    @PostMapping("/modify/{todoId}")
//    public ResponseEntity<String> todoModify(TodoDto todoDto) {
//
//        service.modify(todoDto);
//
//        return ResponseEntity.ok(todoDto.getTodoId() + "번 TODO가 수정되었습니다.");
//    }

//    @DeleteMapping("/remove/{todoId}")
//    public ResponseEntity<String> todoRemove(@PathVariable("todoId") Long todoId) {
//
//        service.todoRemove(todoId);
//
//        return ResponseEntity.ok("Todo List가 성공적으로 삭제되었습니다.");
//    }

}
