package kr.bora.api.upload.repository;

import kr.bora.api.upload.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

}
