package kr.bora.api.subtask.repository;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    @Query("select a from SubTask a order by a.subTaskId desc")
    List<SubTask> subTaskFindAll();


    @Modifying
    @Query("delete from SubTask sb where sb.todo.todoId = :todoId")
    void subTaskDelete(@Param("todoId") Long todoId);


    List<SubTask> getSubTasksByTodoOrderByRegDate(Todo todo);

}
