package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.command.TodoRequestCommand;
import kr.bora.api.todo.domain.Todo;

import java.util.List;
public interface SubTaskService {

    Long subTaskSave(SubTaskDto subTaskDto, Long TodoId);

    void subTaskRemove(Long subTaskId);

    void subTaskModify(Long subTaskId, SubTaskDto subTaskDto);

    List<SubTaskDto> subTaskList(Long todoId);

    default SubTask dtoToEntity(SubTaskDto subTaskDto) {
        return SubTask.builder()
                .title(subTaskDto.getTitle())
                .start(subTaskDto.getStart())
                .end(subTaskDto.getEnd())
                .assignee(subTaskDto.getAssignee())
                .doneTime(subTaskDto.getDoneTime())
                .point(subTaskDto.getPoint())
                .todo(Todo.builder().todoId(subTaskDto.getTodoId()).build())
                .user((subTaskDto.getUserId()).saveId(subTaskDto.getUserId()))
                .build();
    }

    default SubTaskDto entityToDto(SubTask subTask) {
        TodoRequestCommand.TodoRequest users = TodoRequestCommand.TodoRequest.builder().build();
        return SubTaskDto.builder()
                .subTaskId(subTask.getSubTaskId())
                .userId(users.toDto().getUserId())
                .title(subTask.getTitle())
                .start(subTask.getStart())
                .end(subTask.getEnd())
                .assignee(subTask.getAssignee())
                .regDate(subTask.getRegDate())
                .modDate(subTask.getModDate())
                .doneTime(subTask.getDoneTime())
                .point(subTask.getPoint())
                .todoId(subTask.getTodo().getTodoId())
                .subtaskType(subTask.getSubTaskType())
                .build();

    }



}
