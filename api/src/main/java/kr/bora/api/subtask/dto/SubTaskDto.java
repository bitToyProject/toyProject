package kr.bora.api.subtask.dto;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@NoArgsConstructor
public class SubTaskDto {

    private Long subTaskId;

    private UserRequestDto userId;

    private TodoDto todoId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder

    public SubTaskDto(Long subTaskId, UserRequestDto userId, TodoDto todoId, String title, String start, String end, String assignee, LocalDateTime regDate, LocalDateTime modDate) {
        this.subTaskId = subTaskId;
        this.userId = userId;
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
