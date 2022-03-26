package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Getter
@NoArgsConstructor
public class TodoDto {

    private Long todoId;
    @JsonIgnore
    private TodoUserDto userId;
    @NotNull(message="제목은 필수 입력 값입니다.")
    private String title;
    @NotNull(message="시작일은 필수 입력 값입니다.")
    private String start;

    @NotNull(message="종료일은은 필수입력 값입니다.")
    private String end;

    private String description;

    private String assignee;

    private TodoUserDto nickname;

    private Integer priority;

    private Boolean done;

    private Integer point = 0;
    @JsonIgnore
    private String doneTime;

    private String regDate;

    @JsonIgnore
    private String modDate;

    @Builder
    public TodoDto(Long todoId, TodoUserDto userId, String title, String start, String end, String description, String assignee, TodoUserDto nickname, Integer priority, Boolean done, Integer point, String doneTime, String regDate, String modDate) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.assignee = assignee;
        this.nickname = nickname;
        this.priority = priority;
        this.done = done;
        this.point = point;
        this.doneTime = doneTime;
        this.regDate = regDate;
        this.modDate = modDate;
    }

}
