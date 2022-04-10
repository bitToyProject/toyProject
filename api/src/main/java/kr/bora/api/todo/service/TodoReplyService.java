package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.dto.request.TodoReplyRequestDto;

import java.util.List;

public interface TodoReplyService {
    Long todoReplySave(TodoReplyRequestDto todoReplyDto, Long todoId);

    void todoReplyRemove(Long todoRno);

    List<TodoReplyDto> todoReplyList(Long todoId);

    default TodoReplyDto entityTodoReplyDto(TodoReply todoReply) {
        TodoReplyRequestDto users = TodoReplyRequestDto.builder().build();
        return TodoReplyDto.builder()
                .todoId(todoReply.getTodo().getTodoId())
                .userId(users.toReplyDto(todoReply.getTodo().getTodoId()).getUserId())
                .todoReplyId(todoReply.getTodoRno())
                .text(todoReply.getText())
                .todoReplyer(todoReply.getTodoReplyer())
                .regDate(todoReply.getRegDate())
                .modDate(todoReply.getModDate())
                .build();

    }

    default TodoReply dtoTodoReplyEntity(TodoReplyRequestDto todoReplyDto) {
        return TodoReply.builder()
                .todoRno(todoReplyDto.getTodoReplyId())
                .text(todoReplyDto.getText())
                .todoReplyer(todoReplyDto.getTodoReplyer())
                .todo(Todo.builder().todoId(todoReplyDto.getTodoId()).build())
                .user((todoReplyDto.getUserId()).saveId(todoReplyDto.getUserId()))
                .build();
    }
}
