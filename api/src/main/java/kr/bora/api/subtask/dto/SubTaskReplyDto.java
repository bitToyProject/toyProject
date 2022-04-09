package kr.bora.api.subtask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubTaskReplyDto {

    private Long subtaskRno;

    private String text;

    private String subtaskReplyer;

    private String regDate;

    private String modDate;

    @Builder
    public SubTaskReplyDto(Long subtaskRno, String text, String subtaskReplyer, String regDate, String modDate) {
        this.subtaskRno = subtaskRno;
        this.text = text;
        this.subtaskReplyer = subtaskReplyer;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public void setTodoReplyer(String subtaskReplyer) {
        this.subtaskReplyer = subtaskReplyer;
    }

}
