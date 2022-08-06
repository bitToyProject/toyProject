package kr.bora.api.todo.service;

import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TodoService {

    PageResultDto<TodoDto, Object[]> todoList(PageRequestDto pageRequestDto);

    Long todoSave(TodoDto.Request todoDto, List<MultipartFile> multipartFile);

    TodoDto.Response todoRead(Long todoId);

    void todoModify(Long todoId, TodoDto.Request todoDto,  List<MultipartFile> multipartFile);

    void todoRemove(Long todoId);

    List<String> findAssignee(Long userid); // List

}
