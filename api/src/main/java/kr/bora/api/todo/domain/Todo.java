package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "todos")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private String viewer;

    private Boolean done;

    @LastModifiedDate
    private LocalDateTime doneTime;

    private int priority;

    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Todo(Long todoId, String title, String start, String end, String description, String viewer, Boolean done, LocalDateTime doneTime, int priority, User user) {
        this.todoId = todoId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.done = done;
        this.doneTime = doneTime;
        this.priority = priority;
        this.user = user;
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

    public void changeViewer(String viewer) {
        this.viewer = viewer;
    }

    public void changePriority(int priority) {
        this.priority = priority;
    }

    public void changeDone(Boolean done) {
        this.done = done;
    }



}
