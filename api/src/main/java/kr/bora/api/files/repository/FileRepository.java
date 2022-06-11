package kr.bora.api.files.repository;

import kr.bora.api.files.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
