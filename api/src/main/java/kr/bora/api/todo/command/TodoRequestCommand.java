package kr.bora.api.todo.command;

import kr.bora.api.todo.domain.TodoType;
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
        @NotNull(message = "제목은 필수 입력 값입니다.")
        private String title;
        @NotNull(message = "시작일은 필수 입력 값입니다.")
        private String start;
        @NotNull(message = "종료일은 필수 입력 값입니다.")
        private String end;
        @NotNull(message = "상세 할 일은 필수 입력 값입니다.")
        private String description;
        private String assignee;
        private Integer priority;
        private Integer point = 0;

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
                    .todoType(TodoType.TODO)
                    .build();
        }

    }

}
