package kr.bora.api.todo.service;

import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.request.TodoRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.upload.domain.TodoFileUpload;
import kr.bora.api.upload.dto.TodoFileUploadDto;
import kr.bora.api.upload.repository.FileUploadRepository;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final SubTaskRepository subTaskRepository;
    private final UserRepository userRepository;

    private final FileUploadRepository fileUploadRepository;

    /**
     * Todo 리스트
     *
     * @param pageRequestDto
     * @return
     */
    @Override
    public PageResultDto todoList(PageRequestDto pageRequestDto) {

        Function<Object[], TodoDto> fn = (arr -> entityToDtoForList((Todo) arr[0], (TodoFileUploadDto) arr[1]));
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
     * @param todoRequestDto
     * @return
     */
    @Override
    @Transactional
    public Long todoSave(TodoRequestDto todoRequestDto) {
        UserResponseDto userNickname = getUserNickname();
        todoRequestDto.setNickname(userNickname.getNickname());
        Todo todo = toEntitySaveTodo(todoRequestDto);

        repository.save(todo);

        List<TodoFileUploadDto> todoFiles = todoRequestDto.getTodoFileDtoList();

        if (todoFiles != null && todoFiles.size() > 0) {
            todoFiles.forEach(f -> {
                TodoFileUpload todoFileUpload = dtoEntityFiles(f);
                todoFileUpload.setTodo(todo);
                fileUploadRepository.save(todoFileUpload);
            });
        }

        return todoRequestDto.getTodoId();
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
    @Override
    @Transactional
    public void todoModify(Long todoId, TodoDto todoDto) {

        Todo todo = repository.getById(todoId);

        // todo 변경 메서드 모음
        changeTodo(todoDto, todo);

        repository.save(todo);
    }

    /**
     * Todo 삭제
     *
     * @param todoId
     */
    @Override
    @Transactional
    public void todoRemove(Long todoId) {
        subTaskRepository.subTaskDelete(todoId);
        fileUploadRepository.deleteByTodoId(todoId);
        repository.deleteById(todoId);
    }


    // Todo 작성자 - 현재 사용자 닉네임
    private UserResponseDto getUserNickname() {
        UserResponseDto userNickname = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of).orElseThrow();
        return userNickname;
    }

    private void changeTodo(TodoDto todoDto, Todo todo) {
        todo.changeTitle(todoDto.getTitle());
        todo.changeDescription(todoDto.getDescription());
        todo.changeStart(todoDto.getStart());
        todo.changeEnd(todoDto.getEnd());
        todo.changeAssignee(todoDto.getAssignee());
        todo.changePriority(todoDto.getPriority());
        todo.changeDoneTime(todoDto.getTodoType() == TodoType.DONE ? todoDto.getDoneTime() : todo.getModDate());
        if (todoDto.getTodoType() == TodoType.DONE && todo.getPoint() == 0) {
            todo.changePoint(todo.getPoint() + 10);
        }
        if (!(todoDto.getTodoType() == TodoType.DONE) && todo.getPoint() == 10) {
            todo.changePoint(todo.getPoint() - 10);
        } else {
            todo.changePoint(todo.getPoint());
        }
        todo.changeTodoType(todoDto.getTodoType());

    }

}
