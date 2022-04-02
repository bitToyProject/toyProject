package kr.bora.api.subtask.dto;

import kr.bora.api.subtask.domain.SubtaskType;
import kr.bora.api.todo.dto.TodoUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Getter
@Setter
@NoArgsConstructor
public class SubTaskDto {

    private Long subTaskId;

    private TodoUserDto userId;

    private Long todoId;
    @NotNull(message="SubTask 제목은 필수 입력 값입니다.")
    private String title;

    @NotNull(message="SubTask 시작일은 필수 입력 값입니다.")
    private String start;

    @NotNull(message="SubTask 종료일은 필수 입력 값입니다.")
    private String end;

    private String assignee;

    private Integer point;

    @LastModifiedDate
    private String doneTime;

    private String regDate;
    private String modDate;

    private SubtaskType subTaskType;

    @Builder
    public SubTaskDto(Long subTaskId, TodoUserDto userId, Long todoId, String title, String start, String end, String assignee, Integer point, String doneTime, String regDate, String modDate, SubtaskType subtaskType) {
        this.subTaskId = subTaskId;
        this.userId = userId;
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
        this.point = point;
        this.doneTime = doneTime;
        this.regDate = regDate;
        this.modDate = modDate;
        this.subTaskType = subtaskType;
    }


}
