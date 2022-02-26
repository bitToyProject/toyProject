package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.TodoLikeRepository;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoLikeService {

    private final TodoRepository todoRepository;
    private final TodoLikeRepository todoLikeRepository;

    public boolean addLike(Long userId, Long todoId) {
        Todo todo = todoRepository.getById(todoId);

        if (isNotAlreadyLike(userId, todo)) {
            return true;
        }
        return false;
    }

    private boolean isNotAlreadyLike(Long userId, Todo todo) {
        return todoLikeRepository.findByIdAndTodo(userId, todo).isEmpty();
    }
}
