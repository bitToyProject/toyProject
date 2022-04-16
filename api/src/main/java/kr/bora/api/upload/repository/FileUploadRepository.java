package kr.bora.api.upload.repository;

import kr.bora.api.upload.domain.TodoFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileUploadRepository extends JpaRepository<TodoFileUpload, Long> {

    @Modifying
    @Query("DELETE FROM TodoFileUpload tf where tf.todo.todoId = :todoId")
    void deleteByTodoId(@Param("todoId") Long todoId);
}
