package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoReplyRepository extends JpaRepository<TodoReply, Long> {


    List<TodoReply> getTodoRepliesByTodoOrderByRegDate(Todo todo);

    @Modifying
    @Query("delete from TodoReply tr where tr.todo.todoId = :todoId")
    void todoReplyDelete(@Param("todoId") Long todoId);

}
