package kr.bora.api.files.repository;

import kr.bora.api.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {

}
