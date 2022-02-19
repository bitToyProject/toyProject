package kr.bora.api.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TodoReplyDto {

    private Long todoReplyId;

    private TodoUserDto userId;

    private Long todoId;

    private String text;

    private String todoReplyer;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    @Builder
    public TodoReplyDto(Long todoReplyId, TodoUserDto userId, Long todoId, String text, String todoReplyer, LocalDateTime regDate, LocalDateTime modDate) {
        this.todoReplyId = todoReplyId;
        this.userId = userId;
        this.todoId = todoId;
        this.text = text;
        this.todoReplyer = todoReplyer;
        this.regDate = regDate;
        this.modDate = modDate;
    }
}
