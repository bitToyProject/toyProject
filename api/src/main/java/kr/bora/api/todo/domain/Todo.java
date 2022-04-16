package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Table(name = "todos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@Audited(withModifiedFlag = true)
@Builder
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

    public void changePoint(Integer point) {
        this.point = point;
    }

    public void changeTodoType(TodoType todoType) {
        this.todoType = todoType;
    }

    public void changeDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    @PrePersist
    public void defaultTodoPoint() {
        this.point = this.point == null ? 0 : this.point;
    }

}
