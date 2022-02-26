package kr.bora.api.todo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.bora.api.todo.command.TodoReplyRequestCommand;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.service.TodoReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags={"2.TodoReply"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
@CrossOrigin(origins ="*")
public class TodoReplyController {

    private final TodoReplyService todoReplyService;

    @ApiOperation(value="Todo 댓글 리스트", notes="Todo 댓글 리스트를 보여줍니다.")
    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<TodoReplyDto>> replyList(@PathVariable("todoId") Long todoId) {
        return ResponseEntity.ok(todoReplyService.getList(todoId));
    }

    @ApiOperation(value="Todo 댓글 등록", notes="Todo 댓글을 등록합니다.")
    @PostMapping("/save/{todoId}")
    public ResponseEntity<Map<String, Object>> replySave(@RequestBody TodoReplyRequestCommand.TodoReplyRequest todoReplyDto, @PathVariable Long todoId) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("Save Success TodoReply", todoReplyDto.toDto(todoId).getTodoId() + "번 Todo의 댓글이 등록되었습니다.");
        todoReplyService.save(todoReplyDto.toDto(todoId), todoId);

        return ResponseEntity.ok(obj);
    }

    @ApiOperation(value="Todo 댓글 삭제", notes="Todo 댓글을 삭제합니다.")
    @DeleteMapping("/remove/{todoReplyId}")
    public ResponseEntity<Map<String, Object>> todoReplyRemove(@PathVariable Long todoReplyId) {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("Result", todoReplyId + "번 댓글 삭제");

        todoReplyService.delete(todoReplyId);

        return ResponseEntity.ok(resultMap);
    }


}
