package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Audited(withModifiedFlag = true)
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

    @Builder
    public SubTaskReply(Long subtaskReplyId, String text, String subtaskReplyer, SubTask subTask, User user) {
        this.subtaskReplyId = subtaskReplyId;
        this.text = text;
        this.subtaskReplyer = subtaskReplyer;
        this.subTask = subTask;
        this.user = user;
    }
}
