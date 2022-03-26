package kr.bora.api.todo.service;

import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
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

    /**
     * Todo 리스트
     *
     * @param pageRequestDto
     * @return
     */
    @Override
    public PageResultDto todoList(PageRequestDto pageRequestDto) {

        Function<Object[], TodoDto> fn = (arr -> entityTodoDto(
                (Todo) arr[0]));
        Page<Object[]> result = repository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("todoId").descending())
        );


        return new PageResultDto(result, fn);
    }

    /**
     * Todo 저장
     *
     * @param todoDto
     * @return
     */
    @Override
    public Long todoSave(TodoDto todoDto) {
        Todo todo = toEntitySaveTodo(todoDto);
        repository.save(todo);
        return todoDto.getTodoId();
    }

    /**
     * Todo 상세 읽기
     *
     * @param todoId
     * @return
     */
    @Override
    public TodoDto todoRead(Long todoId) {
        Todo result = repository.getTodo(todoId);
        return entityTodoDto(result);
    }

    /**
     * Todo 수정
     *
     * @param todoId
     * @param todoDto
     */
    @Transactional
    @Override
    public void todoModify(Long todoId, TodoDto todoDto) {

        Todo todo = repository.getById(todoId);

        todo.changeTitle(todoDto.getTitle());
        todo.changeDescription(todoDto.getDescription());
        todo.changeStart(todoDto.getStart());
        todo.changeEnd(todoDto.getEnd());
        todo.changeAssignee(todoDto.getAssignee());
        todo.changePriority(todoDto.getPriority());
        todo.changeDone(todoDto.getDone());

        todo.changePoint(todoDto.getDone() ? todo.getPoint() + 10 : todo.getPoint() - 10);

        repository.save(todo);
    }

    /**
     * Todo 삭제
     *
     * @param todoId
     */
    @Transactional
    @Override
    public void todoRemove(Long todoId) {
        subTaskRepository.subTaskDelete(todoId);
        repository.deleteById(todoId);
    }


}
