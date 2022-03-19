package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import kr.bora.api.todo.dto.TodoLikeDto;

public interface TodoLikeService {

    boolean addLike(TodoLikeDto todoLikeDto, Long todoId);
    void addLikeCancel(TodoLikeDto todoLikeDto, Long todoId, Long todoLikeId);

    default TodoLike dtoTodoLikeEntity(TodoLikeDto todoLikeDto) {
        return TodoLike.builder()
                .todoLikeId(todoLikeDto.getTodoLikeId())
                .todo(Todo.builder().todoId(todoLikeDto.getTodoId()).build())
                .user((todoLikeDto.getUserId()).saveId(todoLikeDto.getUserId()))
                .build();
    }
}
