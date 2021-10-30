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
    public SubTaskDto(String title, String start, String end, String assignee) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
    }

    // dto->entity
    public SubTask toEntity() {
        return SubTask.builder()
                .title(title)
                .start(start)
                .end(end)
                .assignee(assignee)
                .build();
    }
}
