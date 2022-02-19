package kr.bora.api.todo.service;

import kr.bora.api.todo.controller.TodoRequestCommand;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import kr.bora.api.todo.dto.TodoReplyDto;

import java.util.List;

public interface TodoReplyService {
    Long save(TodoReplyDto todoReplyDto, Long todoId);

    void delete(Long todoRno);

    List<TodoReplyDto> getList(Long todoId);

    default TodoReplyDto entityTodoReplyDto(TodoReply todoReply) {
        TodoRequestCommand.TodoRequest users = TodoRequestCommand.TodoRequest.builder().build();
        return TodoReplyDto.builder()
                .todoId(todoReply.getTodo().getTodoId())
                .userId(users.toDto().getUserId())
                .todoReplyId(todoReply.getTodoRno())
                .text(todoReply.getText())
                .todoReplyer(todoReply.getTodoReplyer())
                .regDate(todoReply.getRegDate())
                .modDate(todoReply.getModDate())
                .build();

    }

    default TodoReply dtoTodoReplyEntity(TodoReplyDto todoReplyDto) {
        return TodoReply.builder()
                .todoRno(todoReplyDto.getTodoReplyId())
                .text(todoReplyDto.getText())
                .todoReplyer(todoReplyDto.getTodoReplyer())
                .todo(Todo.builder().todoId(todoReplyDto.getTodoId()).build())
                .user((todoReplyDto.getUserId()).saveId(todoReplyDto.getUserId()))
                .build();
    }
}
