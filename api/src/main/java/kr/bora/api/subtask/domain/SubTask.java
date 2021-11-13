package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "subtasks")
@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = "todo")
public class SubTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtask_id")
    private Long subTaskId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public SubTask(Long subTaskId, String title, String start, String end, String assignee, Todo todo, User user) {
        this.subTaskId = subTaskId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
        this.todo = todo;
        this.user = user;
    }

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
}
