package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.repository.search.SearchTodoRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(rollbackFor = Exception.class)
public interface TodoRepository extends JpaRepository<Todo, Long>, SearchTodoRepository {
    /**
     * Todo 상세 글
     * @param todoId
     * @return
     */
    @Query("SELECT to, w FROM Todo to LEFT JOIN to.user w where to.todoId = :todoId")
    Todo getTodo(@Param("todoId") Long todoId);

    @Query("SELECT to, w, tf " +
            " FROM Todo to LEFT JOIN to.user w" +
            " LEFT JOIN TodoFileUpload tf on tf.todo = to " +
            "where to.todoId =:todoId group by tf ")
    List<Object[]> getTodoWithTodoFileUpload(@Param("todoId") Long todoId);

    /**
     * Todo 목록
     * @return
     */
    /*JPA N+1문제 해결 방안 JOIN FETCH 또는 Entity Graph */
    @EntityGraph(attributePaths = "user")
    @Query(value = "SELECT a.title FROM Todo a")
    List<Todo> getList();

    /**
     * 사용자 삭제 시 Todo 데이터 삭제
     * @param userId
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Todo t where t.user.userId =:userId")
    void deleteTodoUserId(@Param("userId") long userId);

    Optional<TodoType> findByTodoType(TodoType todoType);

}
