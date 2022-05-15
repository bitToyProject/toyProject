package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.TodoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoFileRepository extends JpaRepository<TodoFile, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TodoFile t where t.todo.todoId=:todoId")
    void todoFileDelete(@Param("todoId") long todoId);
}
