package kr.bora.api.subtask.controller;

import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.Builder;
import lombok.Getter;

public class SubTaskRequestCommand {

    @Getter
    @Builder
    public static class SubTaskRequest {
        private String title;
        private String start;
        private String end;
        private String assignee;
        private TodoDto todo;

        public SubTaskDto toDto(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return SubTaskDto.builder()
                    .userId(UserRequestDto.builder().userId(userId).build())
                    .todoId(todoId)
                    .title(title)
                    .start(start)
                    .end(end)
                    .assignee(assignee)
                    .build();
        }

    }

}
