package kr.bora.api.subtask.service;

import kr.bora.api.subtask.dto.SubTaskReplyDto;

import java.util.List;

public interface SubTaskReplyService {

    Long subtaskReplySave(SubTaskReplyDto.Request subTaskReplyDto, Long subtaskId);

    void subtaskReplyRemove(Long subtaskRno);

    List<SubTaskReplyDto.Response> subtaskReplyList(Long subtaskId);


}
