package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.TodoNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoNotiRepository extends JpaRepository<TodoNotification, Long> {

    List<TodoNotification> findAllByNotiId(Long id);

    /**
     * Todo 삭제 시 TodoNoti 데이터 삭제
     * @param todoId
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TodoNotification tn where tn.todo.todoId=:todoId")
    void deleteTodoNotificationByTodo(@Param("todoId") Long todoId);
}
