package kr.bora.api.todo.service;

import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.PageRequestDto;
import kr.bora.api.todo.dto.PageResultDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final SubTaskRepository subTaskRepository;

    @Override
    public PageResultDto getList(PageRequestDto pageRequestDto) {

        Function<Object[], TodoDto> fn = (arr -> entityTodoDto(
                (Todo) arr[0]));
        Page<Object[]> result = repository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("todoId").descending())
        );


        return new PageResultDto(result, fn);
    }

    @Override
    public Long save(TodoDto todoDto) {
        Todo todo = toEntitySaveTodo(todoDto);
        repository.save(todo);
        return todoDto.getTodoId();
    }

    @Override
    public TodoDto get(Long todoId) {
        Todo result = repository.getTodo(todoId);

        return entityTodoDto(result);
    }

    @Transactional
    @Override
    public void modify(Long todoId, TodoDto todoDto) {

        Todo todo = repository.getById(todoId);
        todo.changeTitle(todoDto.getTitle());
        todo.changeDescription(todoDto.getDescription());
        todo.changeStart(todoDto.getStart());
        todo.changeEnd(todoDto.getEnd());
        todo.changeViewer(todoDto.getViewer());
        todo.changePriority(todoDto.getPriority());
        todo.changeDone(todoDto.isDone());

        repository.save(todo);
    }

    @Transactional
    @Override
    public void todoRemove(Long todoId) {
        subTaskRepository.subTaskDelete(todoId);
        repository.deleteById(todoId);
    }


}
