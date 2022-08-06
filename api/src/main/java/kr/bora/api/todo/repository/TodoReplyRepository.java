package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoReplyRepository extends JpaRepository<TodoReply, Long> {

    /**
     * Todo 댓글 목록
     * @param todo
     * @return
     */
    List<TodoReply> getTodoRepliesByTodoOrderByRegDate(Todo todo);


    @Query("select tbr.user.userId from TodoReply tbr where tbr.todoRno=:todoRno")
    Long getTodoReplyer(@Param("todoRno") Long todoRno);

    /**
     * Todo 삭제 시 TodoReply 데이터 삭제
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TodoReply tr where tr.todo.todoId=:todoId")
    void deleteTodoReplyByTodoId(@Param("todoId") Long todoId);
}
