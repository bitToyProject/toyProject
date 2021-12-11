package kr.bora.api.todo.service;

import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final SubTaskRepository subTaskRepository;


    @Override
    public List<Todo> getList() {

        return repository.getList();
    }

    @Override
    public Long save(TodoDto todoDto) {
        Todo todo = toEntitySaveUserId(todoDto);
        repository.save(todo);
        return todoDto.getTodoId();
    }

//
//    public CommonResponse<TodoDto> TodoSave(TodoDto todoDto) {
//
//        Todo todo = toEntitySaveUserId(todoDto);
//        repository.save(todo);
//
//        return CommonResponse.success(todoDto);
//    }

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

        repository.save(todo);
    }

    @Transactional
    @Override
    public void todoRemove(Long todoId) {
        subTaskRepository.subTaskDelete(todoId);
        repository.deleteById(todoId);
    }


}
