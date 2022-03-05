package kr.bora.api.todo.controller;

//import kr.bora.api.common.response.CommonResponse;
//import kr.bora.api.todo.domain.Todo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.todo.command.TodoRequestCommand;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Api(tags={"2. Todo"})
@RestController
@Log4j2
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;

    /**
     * Todo 목록
     * @param pageRequestDto
     * @return
     */
    @ApiOperation(value="Todo 리스트", notes="Todo 리스트를 보여줍니다.")
    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<TodoDto, Object[]>> todoList(PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(service.todoList(pageRequestDto));
    }

    /**
     * Todo 등록
     * @param todoDto
     * @return
     */
    @ApiOperation(value="Todo 등록", notes="Todo 리스트를 등록합니다.")
    @PostMapping("/save")
    public ResponseEntity<String> todoSave(@RequestBody TodoRequestCommand.TodoRequest todoDto) {
        service.todoSave(todoDto.toDto());
        return ResponseEntity.ok("ToDo가 정상적으로 등록되었습니다.");
    }

    /**
     * Todo 상세 읽기
     * @param todoId
     * @return
     */
    @ApiOperation(value="Todo 확인", notes="Todo를 확인합니다.")
    @GetMapping("/read/{todoId}")
    public ResponseEntity<TodoDto> todoRead(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.todoRead(todoId));
    }

    /**
     * Todo 변경
     * @param todoId
     * @param todoDto
     * @return
     */
    @ApiOperation(value="Todo 변경", notes=" Todo를 변경합니다.")
    @PutMapping("/modify/{todoId}")
    public ResponseEntity<String> todoModify(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {

        service.todoModify(todoId, todoDto);

        return ResponseEntity.ok(todoId + "번 TODO가 수정되었습니다.");
    }

    /**
     * Todo 삭제
     * @param todoId
     * @return
     */
    @ApiOperation(value="Todo 삭제", notes="Todo를 삭제합니다.")
    @DeleteMapping("/remove/{todoId}")
    public ResponseEntity<String> todoRemove(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {

        service.todoRemove(todoId);

        return ResponseEntity.ok("Todo가 정상적으로 삭제 되었습니다.");
    }



}
