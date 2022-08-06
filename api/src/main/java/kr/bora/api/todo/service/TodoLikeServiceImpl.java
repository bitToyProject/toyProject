package kr.bora.api.todo.service;

import kr.bora.api.common.exception.BoraException;
import kr.bora.api.common.exception.ErrorCode;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoLike;
import kr.bora.api.todo.dto.TodoLikeDto;
import kr.bora.api.todo.repository.TodoLikeRepository;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoLikeServiceImpl implements TodoLikeService {

    private final TodoRepository todoRepository;

    private final TodoLikeRepository todoLikeRepository;

    @Override
    @Transactional
    public boolean addLike(TodoLikeDto.Request todoLikeDto, Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow();
        TodoLike todoLike = todoLikeDto.toEntity(todoId);

        if (alreadyTodoLike(todo, todoLike.getUser())) {
            todoLikeRepository.save(todoLike);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public void addLikeCancel(TodoLikeDto.Request todoLikeDto, Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();

        TodoLike todoLike = todoLikeRepository.findByTodoAndUser(todo, todo.getUser())
                .orElseThrow(() -> new BoraException(ErrorCode.ALREADY_LIKE_CANCEL, "이미 좋아요 취소를 눌렀습니다. 다시 좋아요를 누르세요"));

        if (!alreadyTodoLike(todo, todo.getUser())) {
            todoLikeRepository.delete(todoLike);
        }
    }

    @Override
    public List<String> count(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow();

        Integer todoLikeCnt = todoLikeRepository.countByTodoLike(todo).orElse(0);

        List<String> resultData = Arrays.asList(String.valueOf(todoLikeCnt));

        return resultData;
    }


    // 좋아요 이미 했을 경우 체크
    private boolean alreadyTodoLike(Todo todo, User user) {
        return todoLikeRepository.findByTodoAndUser(todo, user).isEmpty();
    }
}
