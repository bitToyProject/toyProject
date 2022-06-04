package kr.bora.api.todo.service;

import kr.bora.api.team.domain.dto.TeamResponseDto;
import kr.bora.api.teamuser.domain.dto.TeamUserDto;
import kr.bora.api.teamuser.domain.dto.TeamUserResponse;
import kr.bora.api.teamuser.domain.dto.TeamUsersResponseDto;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoPriorityType;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.request.TodoRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;

import java.util.List;

public interface TodoService {

    PageResultDto<TodoDto, Object[]> todoList(PageRequestDto pageRequestDto);

    Long todoSave(TodoRequestDto todoRequestDto);


    TodoDto todoRead(Long todoId);

    void todoModify(Long todoId, TodoDto todoDto, TodoRequestDto todoRequestDto);

    void todoRemove(Long todoId);

    List<String> findAssignee(Long userid); // List



    default TodoDto entityTodoDto(Todo todo) {
        TodoRequestDto users = TodoRequestDto.builder().build();
        return TodoDto.builder()
                .todoId(todo.getTodoId())
                .userId(users.toDto().getUserId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .start(todo.getStart())
                .end(todo.getEnd())
                .assignee(todo.getAssignee())
                .priority(TodoPriorityType.BASIC)
                .regDate(todo.getRegDate())
                .modDate(todo.getModDate())
                .nickname(todo.getNickname())
                .doneTime(todo.getDoneTime())
                .point(todo.getPoint())
                .todoType(TodoType.TODO)
                .build();
    }


    default Todo toEntitySaveTodo(TodoRequestDto dto) {
        TeamUserResponse teamUsers = TeamUserResponse.builder().build();
        return Todo.builder()
                .title(dto.getTitle())
                .start(dto.getStart())
                .end(dto.getEnd())
                .priority(dto.getPriority())
                // 팀에 있는 닉네임  - 팀원
                .assignee(dto.getAssignee())
                .description(dto.getDescription())
                .doneTime(dto.getDoneTime())
                .point(dto.getPoint())
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .nickname(dto.getNickname())
                .todoType(dto.getTodoType())
                .build();
    }


}
