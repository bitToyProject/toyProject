package kr.bora.api.todo.service;

import kr.bora.api.todo.command.TodoRequestCommand;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;

public interface TodoService {

    PageResultDto<TodoDto, Object[]> getList(PageRequestDto pageRequestDto);

    Long save(TodoDto todoDto);

    TodoDto get(Long todoId);

    void modify(Long todoId, TodoDto todoDto);

    void todoRemove(Long todoId);


    default TodoDto entityTodoDto(Todo todo) {
        TodoRequestCommand.TodoRequest users = TodoRequestCommand.TodoRequest.builder().build();
        return TodoDto.builder()
                .todoId(todo.getTodoId())
                .userId(users.toDto().getUserId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .start(todo.getStart())
                .end(todo.getEnd())
                .viewer(todo.getViewer())
                .priority(todo.getPriority())
                .regDate(todo.getRegDate())
                .modDate(todo.getModDate())
                .done(todo.getDone())
                .doneTime(todo.getDoneTime())
                .build();
    }


    default Todo toEntitySaveTodo(TodoDto dto) {
        return Todo.builder()
                .title(dto.getTitle())
                .start(dto.getStart())
                .end(dto.getEnd())
                .priority(dto.getPriority())
                .viewer(dto.getViewer())
                .description(dto.getDescription())
                .done(dto.isDone())
                .doneTime(dto.getDoneTime())
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .build();
    }
}
