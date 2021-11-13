package kr.bora.api.subtask.dto;

import kr.bora.api.subtask.domain.SubTask;
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

    private Long todoId;

    private Long userId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public SubTaskDto(Long subTaskId, Long todoId, Long userId, String title, String start, String end, String assignee) {
        this.subTaskId = subTaskId;
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
    }

}
