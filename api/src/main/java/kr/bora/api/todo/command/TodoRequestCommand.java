package kr.bora.api.todo.command;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class TodoRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TodoRequest {
        @NotNull(message="제목은 필수 입력 값입니다.")
        private String title;
        @NotNull(message="시작일은 필수 입력 값입니다.")
        private String start;
        @NotNull(message="종료일은 필수 입력 값입니다.")
        private String end;
        private String description;
        private String assignee;
        private Integer priority;

        private String username;

        private String nickname;

        private Boolean done;
        private Integer point = 0;
        private String doneTime;

        public TodoDto toDto() {
            Long userId = SecurityUtil.getCurrentUserId();
            return TodoDto.builder()
                    .userId(TodoUserDto.builder().userId(userId).build())
                    .title(title)
                    .start(start)
                    .end(end)
                    .description(description)
                    .assignee(assignee)
                    .priority(priority)
                    .point(point)
                    .done(false)
                    .doneTime(doneTime)
                    .point(point)
                    .build();
        }

    }

}
