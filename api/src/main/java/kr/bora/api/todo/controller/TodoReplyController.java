package kr.bora.api.todo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.service.TodoReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(tags={"5.TodoReply"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/todoreply")
public class TodoReplyController {

    private final TodoReplyService todoReplyService;

    /**
     * Todo 댓글 목록
     * @param todoId
     * @return
     */
    @ApiOperation(value="Todo 댓글 리스트", notes="Todo 댓글 리스트를 보여줍니다.")
    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<TodoReplyDto.Response>> replyList(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {
        return ResponseEntity.ok(todoReplyService.todoReplyList(todoId));
    }

    /**
     * Todo 댓글 등록
     * @param todoReplyDto
     * @param todoId
     * @return
     */
    @ApiOperation(value="Todo 댓글 등록", notes="Todo 댓글을 등록합니다.")
    @PostMapping("/save/{todoId}")
    public ResponseEntity<ApiResponse> replySave(@ApiParam(value="Todo 번호", required=true) @Valid @RequestBody TodoReplyDto.Request todoReplyDto,
                                                 @PathVariable Long todoId) {
        todoReplyService.todoReplySave(todoReplyDto, todoId);
        return ResponseEntity.ok(ApiResponse.success("respose success data", todoReplyDto));
    }

    /**
     * Todo 댓글 삭제
     * @param todoReplyId
     * @return
     */
    @ApiOperation(value="Todo 댓글 삭제", notes="Todo 댓글을 삭제합니다.")
    @DeleteMapping("/remove/{todoReplyId}")
    public ResponseEntity<ApiResponse> todoReplyRemove(@ApiParam(value="TodoReply 번호", required=true) @PathVariable Long todoReplyId) {

        todoReplyService.todoReplyRemove(todoReplyId);

        return ResponseEntity.ok(ApiResponse.success("response success data", todoReplyId + "번 TodoReply가 성공적으로 삭제되었습니다."));
    }


}
