package kr.bora.api.subtask.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Table(name = "subtasks")
@Entity
@Getter
@NoArgsConstructor
public class SubTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subtask_id")
    private Long subTaskId;

    private String title;

    private String start;

    private String end;

    private String assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public SubTask(String title, String start, String end, String assignee) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.assignee = assignee;
    }
}
