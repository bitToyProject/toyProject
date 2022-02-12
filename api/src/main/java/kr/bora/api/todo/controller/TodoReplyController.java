package kr.bora.api.todo.controller;

import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.service.TodoReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class TodoReplyController {

    private final TodoReplyService todoReplyService;

    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<TodoReplyDto>> replyList(@PathVariable("todoId") Long todoId) {
        return ResponseEntity.ok(todoReplyService.getList(todoId));
    }

    @PostMapping("/save/{todoId}")
    public ResponseEntity<Map<String, Object>> replySave(@RequestBody TodoReplyRequestCommand.TodoReplyRequest todoReplyDto, @PathVariable Long todoId) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("Save Success TodoReply", todoReplyDto.toDto(todoId).getTodoId() + "번 Todo의 댓글이 등록되었습니다.");
        todoReplyService.save(todoReplyDto.toDto(todoId), todoId);

        return ResponseEntity.ok(obj);
    }


    @PutMapping("/modify/{todoReplyId}")
    public ResponseEntity<String> todoModify(@PathVariable("todoReplyId") Long todoReplyId, @RequestBody TodoReplyDto todoReplyDto) {
        todoReplyService.modify(todoReplyId, todoReplyDto);

        return ResponseEntity.ok(todoReplyId + "번 댓글이 수정되었습니다.");

    }

    @DeleteMapping("/remove/{todoReplyId}")
    public ResponseEntity<Map<String, Object>> todoReplyRemove(@PathVariable Long todoReplyId) {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("Result", todoReplyId + "번 댓글 삭제");

        todoReplyService.delete(todoReplyId);

        return ResponseEntity.ok(resultMap);
    }


}
