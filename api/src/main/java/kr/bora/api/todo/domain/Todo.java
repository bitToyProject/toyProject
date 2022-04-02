package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Table(name = "todos")
@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = {"user"})
@Audited(withModifiedFlag = true)
public class Todo extends BaseEntity {

    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String assignee;

    private Boolean done;
    private Integer point;

    private String nickname;

    @LastModifiedDate
    private String doneTime;
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TodoType todoType;

    @Builder
    public Todo(Long todoId, String title, String start, String end, String description, String assignee, Boolean done, Integer point, String nickname, String doneTime, Integer priority, User user, TodoType todoType) {
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.assignee = assignee;
        this.done = done;
        this.point = point;
        this.nickname = nickname;
        this.doneTime = doneTime;
        this.priority = priority;
        this.user = user;
        this.todoType = todoType;
    }


    // == Todo 수정 시 변경 메서드 == //
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

    public void changeAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void changePriority(Integer priority) {
        this.priority = priority;
    }

    public void changeDone(Boolean done) {
        this.done = done;
    }

    public void changePoint(Integer point) {
        this.point = point;
    }

    public void changeTodoType(TodoType todoType) {
        this.todoType = todoType;
    }

    @PrePersist
    public void defaultTodoPoint() {
        this.point = this.point == null ? 0 : this.point;
    }

}
