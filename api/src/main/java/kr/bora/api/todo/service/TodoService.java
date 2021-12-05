package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.util.SecurityUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TodoService {

    List<Todo> getList();

    Long save(TodoDto todoDto);

    TodoDto get(Long todoId);

    void modify(Long todoId, TodoDto todoDto);

    void todoRemove(Long todoId);

//    default Map<String, Object> dtoToEntity(TodoDto todoDto) {
//        Map<String, Object> entityMap = new HashMap<>();
//        UserRequestDto users = UserRequestDto.builder().userId(SecurityUtil.getCurrentUserId();).build();
//        Todo todo = Todo.builder()
//                .todoId(todoDto.getTodoId())
//                .title(todoDto.getTitle())
//                .description(todoDto.getDescription())
//                .start(todoDto.getStart())
//                .end(todoDto.getStart())
//                .viewer(todoDto.getViewer())
//                .priority(todoDto.getPriority())
//                .user(users)
//                .build();
//        entityMap.put("todo", todo);
//
//        return entityMap;
//    }


    default TodoDto entityTodoDto(Todo todo, User user) {
        TodoDto users = TodoDto.builder()
                .build();
        return TodoDto.builder()
                .todoId(todo.getTodoId())
                .userId(users.toDto().getUserId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .start(todo.getStart())
                .end(todo.getEnd())
                .viewer(user == null ? "" : user.getUsername())
                .priority(todo.getPriority())
                .build();
    }

     default Todo toEntitySaveUserId(TodoDto dto) {
        return Todo.builder()
                .title(dto.getTitle())
                .start(dto.getStart())
                .end(dto.getEnd())
                .priority(dto.getPriority())
                .viewer(dto.getViewer())
                .description(dto.getDescription())
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .build();
    }
}
