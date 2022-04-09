package kr.bora.api.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.bora.api.todo.domain.TodoType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TodoDto {

    private Long todoId;
    @JsonIgnore
    private TodoUserDto userId;

    @NotNull(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotNull(message = "시작일은 필수 입력 값입니다.")
    private String start;

    @NotNull(message = "종료일은은 필수입력 값입니다.")
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

    @Builder
    public TodoDto(Long todoId,
                   TodoUserDto userId,
                   String title,
                   String start,
                   String end,
                   String description,
                   String assignee,
                   String nickname,
                   Integer priority,
                   Integer point,
                   String doneTime,
                   String regDate,
                   String modDate,
                   TodoType todoType) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.assignee = assignee;
        this.nickname = nickname;
        this.priority = priority;
        this.point = point;
        this.doneTime = doneTime;
        this.regDate = regDate;
        this.modDate = modDate;
        this.todoType = todoType;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
