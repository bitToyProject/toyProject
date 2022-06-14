package kr.bora.api.files.repository;

import io.swagger.v3.oas.annotations.Parameter;
import kr.bora.api.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends JpaRepository<Files, Long> {

    @Query("select f.todo.todoId from Files f where f.todo.todoId =: todoId")
    Long findByTodoId(@Param("todoId") Long todoId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.todo.todoId=:todoId")
    void filesDelete(@Param("todoId") Long todoId);
}
