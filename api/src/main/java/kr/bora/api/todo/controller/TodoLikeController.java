package kr.bora.api.todo.controller;

import kr.bora.api.common.response.ApiResponse;
import kr.bora.api.todo.dto.TodoLikeDto;
import kr.bora.api.todo.service.TodoLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoLikeController {

    private final TodoLikeService todoLikeService;

    @GetMapping("/like/count/{todoId}")
    public ResponseEntity<ApiResponse> todoLikeCount(@PathVariable Long todoId) {

        List<String> count = todoLikeService.count(todoId);

        return ResponseEntity.ok(ApiResponse.success("response todoLike count = ", count));
    }

    @PostMapping("/like/{todoId}")
    public ResponseEntity<ApiResponse> addLike(TodoLikeDto.Request todoLikeDto, @PathVariable Long todoId) {

        boolean result = false;

        if (todoLikeDto.toEntity(todoId) != null) {
            result = todoLikeService.addLike(todoLikeDto, todoId);
        }

        return result ? ResponseEntity.ok(ApiResponse.success("response success data", todoId + "번 게시물의 좋아요를 눌렀습니다")) :
                ResponseEntity.ok(ApiResponse.success("response alreadyLike", todoId + "번 게시물의 이미 좋아요를 눌렀습니다."));
    }

    @DeleteMapping("/like/{todoId}")
    public ResponseEntity<ApiResponse> addLikeCancel(TodoLikeDto.Request todoLikeDto, @PathVariable Long todoId) {

        todoLikeService.addLikeCancel(todoLikeDto, todoId);

        return ResponseEntity.ok(ApiResponse.success("response todoLike cancel", todoId + "번 게시물의 좋아요를 취소했습니다."));
    }

}
