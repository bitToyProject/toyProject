package kr.bora.api.todo.controller;

import kr.bora.api.todo.service.TodoLikeService;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoLikeController {

    private final TodoLikeService todoLikeService;

    @PostMapping("/like/{todoId}")
    public ResponseEntity<String> addLike(@PathVariable Long todoId) {

        Long userId = SecurityUtil.getCurrentUserId();

        boolean result = false;

        if (todoId != null) {
            result = todoLikeService.addLike(userId, todoId);
        }

        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
