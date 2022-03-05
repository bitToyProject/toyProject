package kr.bora.api.todo.command;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TodoRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoRequest {
        private String title;
        private String start;
        private String end;
        private String description;
        private String viewer;
        private Integer priority;
        private String username;
        private Boolean done;
        private Integer point;
        private LocalDateTime doneTime;

        public TodoDto toDto() {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoDto.builder()
                    .userId(TodoUserDto.builder().userId(userId).build())
                    .title(title)
                    .start(start)
                    .end(end)
                    .description(description)
                    .viewer(viewer)
                    .priority(priority)
                    .point(point)
                    .done(false)
                    .doneTime(doneTime)
                    .build();

        }

    }

}
