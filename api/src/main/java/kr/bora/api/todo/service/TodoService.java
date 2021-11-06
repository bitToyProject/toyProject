package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TodoService {

    List<Todo> getList();

    Long TodoSave(TodoDto todoDto);

    TodoDto get(Long todoId);

    void modify(TodoDto todoDto);

    void todoRemove(Long todoId);

    default Map<String, Object> dtoToEntity(TodoDto todoDto) {
        Map<String, Object> entityMap = new HashMap<>();
        User users = User.builder().userId(todoDto.getUserId()).build();
        Todo todo = Todo.builder()
                .todoId(todoDto.getTodoId())
                .title(todoDto.getTitle())
                .description(todoDto.getDescription())
                .start(todoDto.getStart())
                .end(todoDto.getStart())
                .viewer(todoDto.getViewer())
                .priority(todoDto.getPriority())
                .user(users)
                .build();
        entityMap.put("todo", todo);

        return entityMap;
    }

    default TodoDto entityTodoDto(Todo todo, User user) {
        TodoDto todoDto = TodoDto.builder()
                .todoId(todo.getTodoId())
                .userId(user == null ?  1L : user.getUserId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .start(todo.getStart())
                .end(todo.getEnd())
                .viewer(user == null ? "" : user.getUsername())
                .priority(todo.getPriority())
                .build();
        return todoDto;
    }
}
