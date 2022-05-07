package kr.bora.api.todo.repository;

import kr.bora.api.todo.domain.TodoFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoFileUploadRepository extends JpaRepository<TodoFileUpload, Long> {

}
