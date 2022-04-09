package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubTaskReply;
import kr.bora.api.subtask.dto.SubTaskReplyDto;
import kr.bora.api.todo.command.TodoRequestCommand;

import java.util.List;

public interface SubTaskReplyService {

    Long subtaskReplySave(SubTaskReplyDto subTaskReplyDto, Long subtaskId);

    void subtaskReplyRemove(Long subtaskRno);

    List<SubTaskReplyDto> subtaskReplyList(Long subtaskId);

    default SubTaskReplyDto entitySubtaskReplyDto(SubTaskReply subTaskReply) {
        TodoRequestCommand.TodoRequest users = TodoRequestCommand.TodoRequest.builder().build();
        return SubTaskReplyDto.builder()
                .subtaskId(subTaskReply.getSubTask().getSubTaskId())
                .userId(users.toDto().getUserId())
                .subtaskReplyId(subTaskReply.getSubtaskReplyId())
                .text(subTaskReply.getText())
                .subtaskReplyer(subTaskReply.getSubtaskReplyer())
                .regDate(subTaskReply.getRegDate())
                .modDate(subTaskReply.getModDate())
                .build();
    }

    default SubTaskReply dtoSubtaskReplyEntity(SubTaskReplyDto subtaskReplyDto) {
        return SubTaskReply.builder()
                .subtaskReplyId(subtaskReplyDto.getSubtaskReplyId())
                .text(subtaskReplyDto.getText())
                .subtaskReplyer(subtaskReplyDto.getSubtaskReplyer())
                .subTask(SubTask.builder().subTaskId(subtaskReplyDto.getSubtaskId()).build())
                .user((subtaskReplyDto.getUserId()).saveId(subtaskReplyDto.getUserId()))
                .build();

    }

}
