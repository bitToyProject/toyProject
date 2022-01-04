package kr.bora.api.subtask.service;

import kr.bora.api.subtask.controller.SubTaskRequestCommand;
import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.todo.controller.TodoRequestCommand;
import kr.bora.api.todo.domain.Todo;

import java.util.List;

public interface SubTaskService {

    Long save(SubTaskDto subTaskDto);

    void remove(Long subTaskId);

    void modify(Long subTaskId, SubTaskDto subTaskDto);

    List<SubTaskDto> getList(Long todoId);

//    default SubTask dtoToEntity(SubTaskDto subTaskDto) {
//        Todo todo = Todo.builder().todoId(subTaskDto.getTodoId()).build();
//        SubTask subTask = SubTask.builder()
//                .subTaskId(subTaskDto.getSubTaskId())
//                .title(subTaskDto.getTitle())
//                .start(subTaskDto.getStart())
//                .end(subTaskDto.getEnd())
//                .assignee(subTaskDto.getAssignee())
//                .todo(todo)
//                .build();
//
//        return subTask;
//    }

    default SubTask dtoToEntity(SubTaskDto dto) {
        return SubTask.builder()
                .title(dto.getTitle())
                .start(dto.getStart())
                .end(dto.getEnd())
                .assignee(dto.getAssignee())
                .todo((dto.getTodoId()).saveId(dto.getTodoId()))
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .build();
    }

    default SubTaskDto entityToDto(SubTask subTask) {
        TodoRequestCommand.TodoRequest users = TodoRequestCommand.TodoRequest.builder().build();
        SubTaskRequestCommand.SubTaskRequest todos = SubTaskRequestCommand.SubTaskRequest.builder().build();
        return SubTaskDto.builder()
                .subTaskId(subTask.getSubTaskId())
                .userId(users.toDto().getUserId())
                .title(subTask.getTitle())
                .start(subTask.getStart())
                .end(subTask.getEnd())
                .assignee(subTask.getAssignee())
                .regDate(subTask.getRegDate())
                .modDate(subTask.getModDate())
                .todoId(todos.toDto().getTodoId())
                .build();
    }

}
