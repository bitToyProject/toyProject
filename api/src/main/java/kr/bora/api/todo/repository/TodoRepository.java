package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.search.SearchTodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface TodoRepository extends JpaRepository<Todo, Long>, SearchTodoRepository {

    @Query("SELECT to, w FROM Todo to LEFT JOIN to.user w where to.todoId = :todoId")
    Todo getTodo(@Param("todoId") Long todoId);

    /*JPA N+1문제 해결 방안 JOIN FETCH 또는 Entity Graph */
    @Query(value = "SELECT a FROM Todo a join fetch a.user")
    List<Todo> getList();

    // User 삭제 시 Todo 데이터도 삭제
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Todo t where t.user.userId =:userId")
    void deleteTodoUserId(@Param("userId") long userId);

}
