package kr.bora.api.todo.repository;

import kr.bora.api.upload.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoFileRepository extends JpaRepository<FileUpload, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TodoFile t where t.todo.todoId=:todoId and t.user.userId =:userId")
    void deleteTodoFile(@Param("todoId") long todoId, @Param("userId") long userId);
}
