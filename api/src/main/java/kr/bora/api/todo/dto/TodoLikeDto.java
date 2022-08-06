package kr.bora.api.todo.dto;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class TodoLikeDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long todoLikeId;

        private User userId;
        private Long todoId;

        public TodoLike toEntity(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoLike.builder()
                    .user(User.builder().userId(userId).build())
                    .todo(Todo.builder().todoId(todoId).build())
                    .build();
        }
    }

}
