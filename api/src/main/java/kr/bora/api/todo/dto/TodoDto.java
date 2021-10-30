package kr.bora.api.todo.dto;

import kr.bora.api.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Getter
@NoArgsConstructor
public class TodoDto {

    private Long todoId;

    private Long userId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String viewer;

    private int priority;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public TodoDto(String title, String start, String end, String description, String viewer, int priority, LocalDateTime regDate, LocalDateTime modDate) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.priority = priority;
    }

    //dto ->  entity
    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .start(start)
                .end(end)
                .description(description)
                .viewer(viewer)
                .priority(priority)
                .build();
    }
}
