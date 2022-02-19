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


    @Override
    @Transactional
    public Long save(TodoReplyDto todoReplyDto, Long todoId) {
        TodoReply todoReply = dtoTodoReplyEntity(todoReplyDto);

        todoReplyRepository.save(todoReply);
        return todoReplyDto.getTodoReplyId();
    }

    @Override
    public List<TodoReplyDto> getList(Long todoId) {
        List<TodoReply> result = todoReplyRepository.getTodoRepliesByTodoOrderByRegDate(Todo.builder().todoId(todoId).build());
        return result.stream().map(this::entityTodoReplyDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void modify(Long todoReplyId, TodoReplyDto todoReplyDto) {
        TodoReply todoReply = todoReplyRepository.getById(todoReplyId);
        todoReply.changeText(todoReplyDto.getText());

        todoReplyRepository.save(todoReply);
    }


    @Override
    public void delete(Long todoRno) {
        todoReplyRepository.deleteById(todoRno);
    }
}
