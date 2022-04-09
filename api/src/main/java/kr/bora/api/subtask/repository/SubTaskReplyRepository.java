package kr.bora.api.subtask.repository;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubTaskReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTaskReplyRepository extends JpaRepository<SubTaskReply,Long> {

    List<SubTaskReply> getSubTaskRepliesBySubTaskOrderByRegDate(SubTask subTask);
}
