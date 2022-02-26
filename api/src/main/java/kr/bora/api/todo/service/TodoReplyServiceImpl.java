package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoReply;
import kr.bora.api.todo.dto.TodoReplyDto;
import kr.bora.api.todo.repository.TodoReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoReplyServiceImpl implements TodoReplyService {

    private final TodoReplyRepository todoReplyRepository;

    /**
     * Todo 댓글 등록
     * @param todoReplyDto
     * @param todoId
     * @return
     */
    @Override
    @Transactional
    public Long todoReplySave(TodoReplyDto todoReplyDto, Long todoId) {
        TodoReply todoReply = dtoTodoReplyEntity(todoReplyDto);

        todoReplyRepository.save(todoReply);
        return todoReplyDto.getTodoReplyId();
    }

    /**
     * Todo 댓글 목록
     * @param todoId
     * @return
     */
    @Override
    public List<TodoReplyDto> todoReplyList(Long todoId) {
        List<TodoReply> result = todoReplyRepository.getTodoRepliesByTodoOrderByRegDate(Todo.builder().todoId(todoId).build());
        return result.stream().map(this::entityTodoReplyDto).collect(Collectors.toList());
    }

    /**
     * Todo 댓글 삭제
     * @param todoRno
     */
    @Override
    public void todoReplyRemove(Long todoRno) {
        todoReplyRepository.deleteById(todoRno);
    }
}
