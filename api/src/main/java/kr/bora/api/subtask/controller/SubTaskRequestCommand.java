package kr.bora.api.subtask.controller;

import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.controller.TodoRequestCommand;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SubTaskRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubTaskRequest {
        private String title;
        private String start;
        private String end;
        private String assignee;
        private String viewer;

        public SubTaskDto toDto() {
            Long userId = SecurityUtil.getCurrentUserId();
            Long todoId = TodoDto.builder().build().getTodoId();
            return SubTaskDto.builder()
                    .userId(UserRequestDto.builder().userId(userId).build())
                    .todoId(TodoDto.builder().todoId(todoId).build())
                    .title(title)
                    .start(start)
                    .end(end)
                    .assignee(assignee)
                    .build();
        }

    }

}
