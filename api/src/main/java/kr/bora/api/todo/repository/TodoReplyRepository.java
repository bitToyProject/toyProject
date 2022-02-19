package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoReplyRepository extends JpaRepository<TodoReply, Long> {


    List<TodoReply> getTodoRepliesByTodoOrderByRegDate(Todo todo);


}
