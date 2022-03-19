package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoLikeRepository extends JpaRepository<TodoLike, Long> {

    Optional<TodoLike> findByTodo(Todo todo);

}
