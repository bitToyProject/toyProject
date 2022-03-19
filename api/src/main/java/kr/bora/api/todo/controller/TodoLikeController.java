package kr.bora.api.todo.controller;

import kr.bora.api.todo.command.TodoLikeRequestCommand;
import kr.bora.api.todo.service.TodoLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoLikeController {

    private final TodoLikeService todoLikeService;

    @PostMapping("/like/{todoId}")
    public ResponseEntity<String> addLike(TodoLikeRequestCommand.TodoLikeRequest todoLikeDto, @PathVariable Long todoId) {

        boolean result = false;

        if (todoLikeDto.toDto(todoId) != null) {
            result = todoLikeService.addLike(todoLikeDto.toDto(todoId), todoId);
        }

        return result ? ResponseEntity.ok("좋아요 등록") : ResponseEntity.ok("이미 좋아요를 눌렀습니다.");
    }

    @DeleteMapping("/like/{todoId}/{todoLikeId}")
    public ResponseEntity<String> addLikeCancel(TodoLikeRequestCommand.TodoLikeRequest todoLikeDto, @PathVariable Long todoId, @PathVariable Long todoLikeId) {

        todoLikeService.addLikeCancel(todoLikeDto.toDto(todoId), todoId, todoLikeId);

        return ResponseEntity.ok("좋아요 취소 완료");
    }
}
