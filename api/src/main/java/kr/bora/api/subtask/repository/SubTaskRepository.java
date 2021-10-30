package kr.bora.api.subtask.repository;

import kr.bora.api.subtask.domain.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
}
