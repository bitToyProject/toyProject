package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.domain.Todo;

import java.util.List;

public interface SubTaskService {

    Long save(SubTaskDto subTaskDto);

    void remove(Long subTaskId);

    void modify(SubTaskDto subTaskDto);

    List<SubTaskDto> getList(Long todoId);

    default SubTask dtoToEntity(SubTaskDto subTaskDto) {
        Todo todo = Todo.builder().todoId(subTaskDto.getTodoId()).build();

        SubTask subTask = SubTask.builder()
                .subTaskId(subTaskDto.getSubTaskId())
                .title(subTaskDto.getTitle())
                .start(subTaskDto.getStart())
                .end(subTaskDto.getEnd())
                .assignee(subTaskDto.getAssignee())
                .todo(todo)
                .build();

        return subTask;
    }

    default SubTaskDto entityToDto(SubTask subTask) {
        SubTaskDto subTaskDto = SubTaskDto.builder()
                .subTaskId(subTask.getSubTaskId())
                .title(subTask.getTitle())
                .start(subTask.getStart())
                .end(subTask.getEnd())
                .assignee(subTask.getAssignee())
                .todoId(subTask.getTodo().getTodoId())
                .build();

        return subTaskDto;
    }

}
