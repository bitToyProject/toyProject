package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "todos")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Audited(withModifiedFlag = true)
@ToString(exclude = {"user", "todoReplies"})
@DynamicInsert // @PrePersist의 다른 방법, 컬럼에 @ColumnDefault("값") 선언 해야함(기본 값 설정)
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

    @ColumnDefault("0")
    private Integer point;

    private String nickname;

    @LastModifiedDate
    private LocalDateTime doneTime;
    @Enumerated(EnumType.STRING)
    private TodoType todoType;
    @Enumerated(EnumType.ORDINAL)
    private TodoPriorityType priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<TodoReply> todoReplies;

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

    public void changePriority(TodoPriorityType priority) {
        this.priority = priority;
    }

    public void changePoint(Integer point) {
        this.point = point;
    }

    public void changeTodoType(TodoType todoType) {
        this.todoType = todoType;
    }

    public void changeDoneTime(LocalDateTime doneTime) {
        this.doneTime = doneTime;
    }

}
