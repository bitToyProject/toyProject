package kr.bora.api.todo.dto;

import kr.bora.api.todo.domain.Todo;
import lombok.*;
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
    public TodoDto(Long todoId, Long userId, String title, String start, String end, String description, String viewer, int priority) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.priority = priority;
    }



}
