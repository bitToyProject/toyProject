package kr.bora.api.subtask.repository;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubTaskReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubTaskReplyRepository extends JpaRepository<SubTaskReply, Long> {

    List<SubTaskReply> getSubTaskRepliesBySubTaskOrderByRegDate(SubTask subTask);

    @Query("select sbr.user.userId from SubTaskReply sbr where sbr.subtaskReplyId =:subtaskReplyId")
    Long getSubTaskReplyer(@Param("subtaskReplyId") Long subtaskReplyId);


}
