package kr.bora.api.todo.service;

import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.teamuser.repository.TeamUserRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.request.TodoRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.todo.repository.TodoFileRepository;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;
    private final SubTaskRepository subTaskRepository;
    private final UserRepository userRepository;
    private final TodoFileRepository todoFileRepository;

    private final TeamUserRepository teamUserRepository;
    /**
     * Todo 리스트
     *
     * @param pageRequestDto
     * @return
     */
    @Override
    public PageResultDto todoList(PageRequestDto pageRequestDto) {

        Function<Object[], TodoDto> fn = (arr -> entityTodoDto((Todo) arr[0]));
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
    public void todoModify(Long todoId, TodoDto todoDto, TodoRequestDto todoRequestDto) {

        Todo todo = repository.getById(todoId);

        // todo 변경 메서드 모음
        changeTodo(todoDto, todo);

        changeAssignee(todo, todoRequestDto);
        repository.save(todo);
    }


    // Assignee 변경
    private void changeAssignee(Todo todo, TodoRequestDto todoRequestDto) {

        // 사용자 닉네임 값 가져오기
        UserResponseDto userNickname = getUserNickname();
        String nickname = todoRequestDto.setNickname(userNickname.getNickname());

        // 닉네임 가져와서 -> change
        todo.changeAssignee(nickname);
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
        todoFileRepository.todoFileDelete(todoId);
        repository.deleteById(todoId);
    }

    @Override
    public List<String> findAssignee(Long userId) {
        List<Todo> assgineeNotification = repository.findAssgineeNotification(userId);

        List<String> collect = assgineeNotification.stream().map(Todo::getAssignee).collect(Collectors.toList());
        String single = assgineeNotification.stream().map(Todo::getNickname).findFirst().get();

        String assignee = "";
        for (int i = 0; i < collect.size(); i++) {
            assignee = collect.get(i).toString();
        }
        log.info(assignee);

        return Collections.singletonList(single + "님과" + assignee + "님이 동맹");
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
        todo.changeAssignee(todoDto.getNickname());
        todo.changePriority(todoDto.getPriority());
        todo.changeDoneTime(todoDto.getTodoType() == TodoType.DONE ? todoDto.getDoneTime() : todo.getModDate());
        changeTodoPoint(todoDto, todo);
        todo.changeTodoType(todoDto.getTodoType());

    }


    // TodoPoint 변경
    private void changeTodoPoint(TodoDto todoDto, Todo todo) {
        if (todoDto.getTodoType() == TodoType.DONE && todo.getPoint() == 0) {
            todo.changePoint(todo.getPoint() + 10);
        }
        if (!(todoDto.getTodoType() == TodoType.DONE) && todo.getPoint() == 10) {
            todo.changePoint(todo.getPoint() - 10);
        } else {
            todo.changePoint(todo.getPoint());
        }
    }

}
