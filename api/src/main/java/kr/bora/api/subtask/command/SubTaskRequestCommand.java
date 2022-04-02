package kr.bora.api.subtask.command;

import kr.bora.api.subtask.domain.SubtaskType;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class SubTaskRequestCommand {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubTaskRequest {
        @NotNull(message="SubTask 제목은 필수 입력 값입니다.")
        private String title;
        @NotNull(message="SubTask 시작일은 필수 입력 값입니다.")
        private String start;
        @NotNull(message="SubTask 종료일은 필수 입력 값입니다.")
        private String end;
        private String assignee;
        private Integer point;

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
                    .point(point)
                    .subtaskType(SubtaskType.SUBTASK)
                    .build();
        }

    }

}
