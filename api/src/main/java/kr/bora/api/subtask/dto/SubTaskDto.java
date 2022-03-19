package kr.bora.api.subtask.dto;

import kr.bora.api.todo.dto.TodoUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    private Boolean done;

    private Integer point;

    @LastModifiedDate
    private LocalDateTime doneTime;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public SubTaskDto(Long subTaskId, TodoUserDto userId, Long todoId, String title, String start, String end, String assignee, Boolean done, Integer point, LocalDateTime doneTime, LocalDateTime regDate, LocalDateTime modDate) {
        this.subTaskId = subTaskId;
        this.userId = userId;
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
        this.done = done;
        this.point = point;
        this.doneTime = doneTime;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
