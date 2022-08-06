package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import kr.bora.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoLikeRepository extends JpaRepository<TodoLike, Long> {

    Optional<TodoLike> findByTodoAndUser(Todo todo, User user);

    Optional<Integer> countByTodoLike(Todo todo);

    /**
     * Todo 삭제 시 TodoLike 데이터 삭제
     * @param todoId
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TodoLike tl where tl.todo.todoId=:todoId")
    void deleteTodoLikeByTodoId(@Param("todoId") Long todoId);
}
