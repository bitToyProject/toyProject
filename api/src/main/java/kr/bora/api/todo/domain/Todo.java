package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.TodoRespondDto;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "todos")
@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = {"user"})
public class Todo extends BaseEntity {

    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String viewer;

    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Todo(Long todoId, String title, String start, String end, String description, String viewer, int priority, User user) {
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.priority = priority;
        this.user = user;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changeStart(String start) {
        this.start = start;
    }

    public void changeEnd(String end) {
        this.end = end;
    }

    public void changeViewer(String viewer) {
        this.viewer = viewer;
    }

    public void changePriority(int priority) {
        this.priority = priority;
    }

    public TodoRespondDto toDtoRespond(Todo todo){
        return TodoRespondDto.builder()
            .todoId(todo.getTodoId())
            .title(todo.getTitle())
            .start(todo.start)
            .end(todo.end)
            .description(todo.getDescription())
            .viewer(todo.getViewer())
            .priority(todo.getPriority())
            .user(todo.getUser().getUserId())
            .build();
    }



}
