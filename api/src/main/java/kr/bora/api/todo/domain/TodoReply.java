package kr.bora.api.todo.domain;

import kr.bora.api.common.domain.BaseEntity;
import kr.bora.api.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "todoReply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Audited(withModifiedFlag = true)
public class TodoReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoRno;

    private String text;

    private String todoReplyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public TodoReply(Long todoRno, String text, String todoReplyer, Todo todo, User user) {
        this.todoRno = todoRno;
        this.text = text;
        this.todoReplyer = todoReplyer;
        this.todo = todo;
        this.user = user;
    }

}
