package kr.bora.api.subtask.service;

import kr.bora.api.subtask.dto.SubTaskDto;

import java.util.List;
public interface SubTaskService {

    Long subTaskSave(SubTaskDto.Request subTaskDto , Long TodoId);

    SubTaskDto.Response subTaskRead(Long subTaskId);

    void subTaskRemove(Long subTaskId);

    void subTaskModify(Long subTaskId, SubTaskDto.Request subTaskDto);

    List<SubTaskDto.Response> subTaskList(Long todoId);

}
