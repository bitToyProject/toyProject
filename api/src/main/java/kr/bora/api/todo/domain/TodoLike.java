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
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Audited(withModifiedFlag = true)
public class TodoLike extends BaseEntity {

    @Id
    @GeneratedValue
    private Long todoLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @Builder
    public TodoLike(Long todoLikeId, User user, Todo todo) {
        this.todoLikeId = todoLikeId;
        this.user = user;
        this.todo = todo;
    }
}
