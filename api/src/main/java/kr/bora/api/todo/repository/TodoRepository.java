package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT to, w FROM Todo to LEFT JOIN to.user w where to.todoId = :todoId" )
    List<Object[]> getTodo(@Param("todoId") Long todoId);

    /*JPA N+1문제 해결 방안 JOIN FETCH 또는 Entity Graph */
    @Query(value = "SELECT a FROM Todo a join fetch a.user")
    List<Todo> getList();
}
