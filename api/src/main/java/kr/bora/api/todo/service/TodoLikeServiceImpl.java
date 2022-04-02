package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import kr.bora.api.todo.dto.TodoLikeDto;
import kr.bora.api.todo.repository.TodoLikeRepository;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TodoLikeServiceImpl implements TodoLikeService {

    private final TodoRepository todoRepository;

    private final TodoLikeRepository todoLikeRepository;

    @Override
    @Transactional
    public boolean addLike(TodoLikeDto todoLikeDto, Long todoId) {
        TodoLike todoLike = dtoTodoLikeEntity(todoLikeDto);
        Todo todo = todoRepository.findById(todoId).orElseThrow();

        if (alreadyTodoLike(todo)) {
            todoLikeRepository.save(todoLike);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void addLikeCancel(TodoLikeDto todoLikeDto, Long todoId, Long todoLikeId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();
        if (!alreadyTodoLike(todo)) {
            todoLikeRepository.deleteById(todoLikeId);
        }
    }


    // 좋아요 이미 했을 경우 체크
    private boolean alreadyTodoLike(Todo todo) {
        return todoLikeRepository.findByTodo(todo).isEmpty();
    }
}
