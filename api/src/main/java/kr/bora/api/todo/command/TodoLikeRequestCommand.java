package kr.bora.api.todo.command;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoLikeDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TodoLikeRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoLikeRequest{

        private TodoDto todo;

        public TodoLikeDto toDto(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoLikeDto.builder()
                    .userId(TodoUserDto.builder().userId(userId).build())
                    .todoId(todoId)
                    .build();
        }
    }
}
