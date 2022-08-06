package kr.bora.api.todo.service;

import kr.bora.api.todo.dto.TodoReplyDto;

import java.util.List;

public interface TodoReplyService {
    Long todoReplySave(TodoReplyDto.Request todoReplyDto, Long todoId);

    void todoReplyRemove(Long todoRno);

    List<TodoReplyDto.Response> todoReplyList(Long todoId);

}
