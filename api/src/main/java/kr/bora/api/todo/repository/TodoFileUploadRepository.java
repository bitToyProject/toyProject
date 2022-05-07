package kr.bora.api.todo.repository;

import kr.bora.api.upload.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoFileUploadRepository extends JpaRepository<FileUpload, Long> {

}
