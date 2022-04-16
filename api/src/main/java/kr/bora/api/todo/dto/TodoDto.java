package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.bora.api.todo.domain.TodoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

    private Long todoId;
    @JsonIgnore
    private TodoUserDto userId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String assignee;

    private String nickname;

    private Integer priority;

    private Integer point = 0;
    @JsonIgnore
    private String doneTime;

    private String regDate;

    @JsonIgnore
    private String modDate;

    private TodoType todoType;

}
