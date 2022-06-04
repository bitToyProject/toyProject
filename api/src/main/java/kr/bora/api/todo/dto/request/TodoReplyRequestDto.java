package kr.bora.api.todo.dto.request;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoUserDto;
import kr.bora.api.user.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoReplyRequestDto {
    private Long todoReplyId;
    private Long todoId;
    private TodoUserDto userId;
    private String text;

    private TodoDto todo;
    private String todoReplyer;

    public TodoReplyRequestDto toReplyDto(Long todoId) {
        Long userId = SecurityUtil.getCurrentUserId();
        return TodoReplyRequestDto.builder()
                .userId(TodoUserDto.builder().userId(userId).build())
                .todoId(todoId)
                .text(text)
                .build();
    }


    public void setTodoReplyer(String todoReplyer) {
        this.todoReplyer = todoReplyer;
    }

}
