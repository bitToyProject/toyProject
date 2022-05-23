package kr.bora.api.integrate.file.domain.repository;

import kr.bora.api.integrate.file.domain.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalFileRepository extends JpaRepository<FileInfo,Long> {

}
