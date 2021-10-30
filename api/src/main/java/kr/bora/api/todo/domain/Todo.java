package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Table(name="todos")
@Entity
@Getter
@NoArgsConstructor
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="todo_id")
    private Long todoId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String viewer;

    private int priority;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Todo(String title, String start, String end, String description, String viewer, int priority) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.priority = priority;
    }
}
