package kr.bora.api.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoReplyDto {

    private Long todoReplyId;

    private TodoUserDto userId;

    private Long todoId;

    private String text;

    private String todoReplyer;

    private String  regDate;

    private String modDate;

    @Builder
    public TodoReplyDto(Long todoReplyId, TodoUserDto userId, Long todoId, String text, String todoReplyer, String regDate, String modDate) {
        this.todoReplyId = todoReplyId;
        this.userId = userId;
        this.todoId = todoId;
        this.text = text;
        this.todoReplyer = todoReplyer;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public void setTodoReplyer(String todoReplyer) {
        this.todoReplyer = todoReplyer;
    }
}
