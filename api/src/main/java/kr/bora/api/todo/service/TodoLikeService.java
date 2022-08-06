package kr.bora.api.todo.service;

import kr.bora.api.todo.dto.TodoLikeDto;

import java.util.List;

public interface TodoLikeService {

    boolean addLike(TodoLikeDto.Request todoLikeDto, Long todoId);
    void addLikeCancel(TodoLikeDto.Request todoLikeDto, Long todoId);

    List<String> count(Long todoId);
}
