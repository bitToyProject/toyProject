package kr.bora.api.subtask.controller;

import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

        private Boolean done;

        private LocalDateTime doneTime;

        private TodoDto todo;

        public SubTaskDto toDto(Long todoId) {
            Long userId = SecurityUtil.getCurrentUserId();
            return SubTaskDto.builder()
                    .userId(TodoUserDto.builder().userId(userId).build())
                    .todoId(todoId)
                    .title(title)
                    .start(start)
                    .end(end)
                    .assignee(assignee)
                    .done(false)
                    .doneTime(doneTime)
                    .build();
        }

    }

}
