package kr.bora.api.todo.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final SubTaskRepository subTaskRepository;


//    @Override
//    public List<Todo> getList() {
//
//        return repository.getList();
//    }


    public CommonResponse<TodoDto> TodoSave(TodoDto todoDto) {

        Todo todo = todoDto.toEntitySaveUserId(todoDto);
        repository.save(todo);

        return CommonResponse.success(todoDto);
    }

//    @Override
//    public TodoDto get(Long todoId) {
//        List<Object[]> result = repository.getTodo(todoId);
//        Todo todo = (Todo) result.get(0)[0];
//        User user = (User) result.get(0)[1];
//
//        return entityTodoDto(todo, user);
//    }

//    @Transactional
//    @Override
//    public void modify(TodoDto todoDto) {
////        Map<String, Object> entityMap = dtoToEntity(todoDto);
//        Todo todo = repository.getById(todoDto.getTodoId());
//
//        todo.changeTitle(todoDto.getTitle());
//        todo.changeDescription(todoDto.getDescription());
//        todo.changeStart(todoDto.getStart());
//        todo.changeEnd(todoDto.getEnd());
//        todo.changeViewer(todoDto.getViewer());
//        todo.changePriority(todoDto.getPriority());
//
//        repository.save(todo);
//    }
//
//    @Transactional
//    @Override
//    public void todoRemove(Long todoId) {
//        subTaskRepository.subTaskDelete(todoId);
//        repository.deleteById(todoId);
//    }


}
