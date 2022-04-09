package kr.bora.api.subtask.dto;

import kr.bora.api.todo.dto.TodoUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubTaskReplyDto {

    private Long subtaskReplyId;

    private TodoUserDto userId;

    private Long subtaskId;

    private String text;

    private String subtaskReplyer;

    private String regDate;

    private String modDate;

    @Builder
    public SubTaskReplyDto(Long subtaskReplyId, TodoUserDto userId, Long subtaskId, String text, String subtaskReplyer, String regDate, String modDate) {
        this.subtaskReplyId = subtaskReplyId;
        this.userId = userId;
        this.subtaskId = subtaskId;
        this.text = text;
        this.subtaskReplyer = subtaskReplyer;
        this.regDate = regDate;
        this.modDate = modDate;
    }



    public void setTodoReplyer(String subtaskReplyer) {
        this.subtaskReplyer = subtaskReplyer;
    }

}
