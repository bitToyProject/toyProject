package kr.bora.api.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@NoArgsConstructor
@Log4j2
public class TodoDto {

    private Long todoId;
    private TodoUserDto userId;
    private String title;

    private String start;

    private String end;

    private String description;

    private String viewer;

    private TodoUserDto nickname;

    private int priority;

    private boolean done;

    private LocalDateTime doneTime;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public TodoDto(Long todoId, TodoUserDto userId, String title, String start, String end, String description, String viewer, TodoUserDto nickname, int priority, boolean done, LocalDateTime doneTime, LocalDateTime regDate, LocalDateTime modDate) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.nickname = nickname;
        this.priority = priority;
        this.done = done;
        this.doneTime = doneTime;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
