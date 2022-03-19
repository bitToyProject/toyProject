package kr.bora.api.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoLikeDto {

    private Long todoLikeId;
    private TodoUserDto userId;

    private Long todoId;

    @Builder
    public TodoLikeDto(Long todoLikeId, TodoUserDto userId, Long todoId) {
        this.todoLikeId = todoLikeId;
        this.userId = userId;
        this.todoId = todoId;
    }
}
