package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Table(name = "subtask_reply")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Audited(withModifiedFlag = true)
@ToString(exclude = {"subTask", "user"})
public class SubTaskReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subtaskReplyId;

    private String text;

    private String subtaskReplyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subtask_id")
    private SubTask subTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글 수정 사용 시 변경 메서드 -> 필요 시 사용
    public void changeSubtaskText(String text) {
        this.text = text;
    }

}
