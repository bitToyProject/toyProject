package kr.bora.api.todo.service;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.request.TodoRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.upload.domain.TodoFileUpload;
import kr.bora.api.upload.dto.TodoFileUploadDto;

public interface TodoService {

    PageResultDto<TodoDto, Object[]> todoList(PageRequestDto pageRequestDto);

    Long todoSave(TodoRequestDto todoRequestDto, TodoFileUploadDto todoFileUploadDto);

    TodoDto todoRead(Long todoId);

    void todoModify(Long todoId, TodoDto todoDto);

    void todoRemove(Long todoId);


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
                .priority(todo.getPriority())
                .regDate(todo.getRegDate())
                .modDate(todo.getModDate())
                .nickname(todo.getNickname())
                .doneTime(todo.getDoneTime())
                .point(todo.getPoint())
                .todoType(TodoType.TODO)
                .build();
    }


    default Todo toEntitySaveTodo(TodoRequestDto dto) {
        return Todo.builder()
                .title(dto.getTitle())
                .start(dto.getStart())
                .end(dto.getEnd())
                .priority(dto.getPriority())
                .assignee(dto.getAssignee())
                .description(dto.getDescription())
                .doneTime(dto.getDoneTime())
                .point(dto.getPoint())
                .user((dto.getUserId()).saveId(dto.getUserId()))
                .nickname(dto.getNickname())
                .todoType(dto.getTodoType())
                .build();
    }

    default TodoFileUpload dtoEntityFiles(TodoFileUploadDto todoFileUploadDto) {
        return TodoFileUpload.builder()
                .uuid(todoFileUploadDto.getUuid())
                .originalFilename(todoFileUploadDto.getOriginalFilename())
                .saveFilename(todoFileUploadDto.getUuid() + "__" + todoFileUploadDto.getOriginalFilename())
                .build();
    }

    default TodoFileUploadDto entityToDtoFiles(TodoFileUpload todoFileUpload) {
        TodoRequestDto users = TodoRequestDto.builder().build();
        return TodoFileUploadDto.builder()
                .fileId(todoFileUpload.getFileId())
                .uuid(todoFileUpload.getUuid())
                .originalFilename(todoFileUpload.getOriginalFilename())
                .saveFilename(todoFileUpload.getSaveFilename())
                .todoId(todoFileUpload.getTodo().getTodoId())
                .userId(users.toDto().getUserId())
                .build();


    }

}
