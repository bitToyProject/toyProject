package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.repository.TodoReplyRepository;
import kr.bora.api.user.dto.UserDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoReplyServiceImpl implements TodoReplyService {

    private final TodoReplyRepository todoReplyRepository;
    private final UserRepository userRepository;

    /**
     * Todo 댓글 등록
     *
     * @param todoReplyDto
     * @param todoId
     * @return
     */
    @Override
    @Transactional
    public Long todoReplySave(TodoReplyDto.Request todoReplyDto, Long todoId) {
        UserDto.UserResponse replyer = getUserNickname();

        todoReplyDto.setTodoReplyer(replyer.getNickname());

        TodoReply todoReply = todoReplyDto.toEntity(todoId);
        todoReplyRepository.save(todoReply);

        return todoReplyDto.getTodoReplyId();
    }

    /**
     * Todo 댓글 목록
     *
     * @param todoId
     * @return
     */
    @Override
    public List<TodoReplyDto.Response> todoReplyList(Long todoId) {
        List<TodoReply> result = todoReplyRepository.
                getTodoRepliesByTodoOrderByRegDate(Todo.builder().todoId(todoId).build());

        return result.stream().map(TodoReplyDto.Response::new).collect(Collectors.toList());
    }

    /**
     * Todo 댓글 삭제
     *
     * @param todoRno
     */
    @Override
    @Transactional
    public void todoReplyRemove(Long todoRno) {
        Long loginUserId = SecurityUtil.getCurrentUserId();

        Long todoReplyerId = todoReplyRepository.getTodoReplyer(todoRno);

        if (loginUserId == todoReplyerId) {
            todoReplyRepository.deleteById(todoRno);
        } else {
            throw new IllegalArgumentException("댓글 작성자 본인만 삭제가 가능합니다.");
        }
    }

    // 댓글 작성자 - 현재 사용자 닉네임
    private UserDto.UserResponse getUserNickname() {
        UserDto.UserResponse replyer = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserDto.UserResponse::new)
                .orElseThrow();
        return replyer;
    }
}
