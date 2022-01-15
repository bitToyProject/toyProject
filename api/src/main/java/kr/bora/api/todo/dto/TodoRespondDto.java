package kr.bora.api.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class TodoRespondDto {
    private Long todoId;
    private String title;
    private String start;
    private String end;
    private String description;
    private String viewer;
    private int priority;
    private Long user;


}
