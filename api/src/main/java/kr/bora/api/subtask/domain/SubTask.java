package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.List;

@Table(name = "subtasks")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Audited(withModifiedFlag = true)
@ToString(exclude = {"todo", "user", "subTaskReplies"})
public class SubTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private Long subTaskId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    private Integer point;

    @LastModifiedDate
    private String doneTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "subTask", cascade = CascadeType.REMOVE)
    private List<SubTaskReply> subTaskReplies;

    @Enumerated(EnumType.STRING)
    private SubtaskType subTaskType;


    // == Subtask 수정 시 변경 메서드 == //
    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeStart(String start) {
        this.start = start;
    }

    public void changeEnd(String end) {
        this.end = end;
    }
    public void changeAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void changePoint(Integer point) {
        this.point = point;
    }

    public void changeSubTaskType(SubtaskType subtaskType) {
        this.subTaskType = subtaskType;
    }

    public void changeDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * Insert 되기 전(persist 되기 전) 실행
     */
    @PrePersist
    public void defaultSubtaskPoint() {
        this.point = this.point == null ? 0 : this.point;
    }
}
