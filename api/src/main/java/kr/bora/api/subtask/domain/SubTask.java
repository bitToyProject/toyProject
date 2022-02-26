package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "subtasks")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "todo")
@Audited(withModifiedFlag = true)
public class SubTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private Long subTaskId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    private Boolean done;

    private Integer point;

    @LastModifiedDate
    private LocalDateTime doneTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public SubTask(Long subTaskId, String title, String start, String end, String assignee, Boolean done, Integer point, LocalDateTime doneTime, Todo todo, User user) {
        this.subTaskId = subTaskId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
        this.done = done;
        this.point = point;
        this.doneTime = doneTime;
        this.todo = todo;
        this.user = user;
    }



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

    public void changeDone(Boolean done) {
        this.done = done;
    }

    public void changePoint(Integer point) {
        this.point = point;
    }
}
