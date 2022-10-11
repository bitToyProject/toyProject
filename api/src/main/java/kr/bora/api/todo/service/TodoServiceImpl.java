package kr.bora.api.todo.service;

import kr.bora.api.borateam.repository.BoraTeamRepository;
import kr.bora.api.borateamuser.domain.BoraTeamUser;
import kr.bora.api.borateamuser.repository.BoraTeamUserRepository;
import kr.bora.api.borateamuser.service.BoraTeamUserService;
import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.repository.FileRepository;
import kr.bora.api.files.service.FileUtil;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.domain.TodoPriorityType;
import kr.bora.api.todo.domain.TodoType;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.dto.searchPageDto.PageRequestDto;
import kr.bora.api.todo.dto.searchPageDto.PageResultDto;
import kr.bora.api.todo.repository.TodoLikeRepository;
import kr.bora.api.todo.repository.TodoNotiRepository;
import kr.bora.api.todo.repository.TodoReplyRepository;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final SubTaskRepository subTaskRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    private final TodoLikeRepository todoLikeRepository;
    private final TodoNotiRepository todoNotiRepository;
    private final TodoReplyRepository todoReplyRepository;

    private final TodoNotiService todoNotiService;

    private final BoraTeamRepository boraTeamRepository;

    private final BoraTeamUserRepository boraTeamUserRepository;

    private final BoraTeamUserService boraTeamUserService;
    private final FileUtil fileUtil;

    /**
     * Todo 리스트
     *
     * @param pageRequestDto
     * @return
     */

    @Override
    public PageResultDto todoList(PageRequestDto pageRequestDto) {

        Function<Object[], TodoDto.Response> fn = (arr -> new TodoDto.Response((Todo) arr[0]));
        Page<Object[]> result = todoRepository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("todoId").descending())
        );


        return new PageResultDto(result, fn);
    }


    /**
     * Todo 저장 (파일 업로드 포함)
     *
     * @param todoRequestDto
     * @param multipartFile
     * @return
     */
    @Override
    @Transactional
    public Long todoSave(TodoDto.Request todoRequestDto, List<MultipartFile> multipartFile, String teamName) {

        // 닉네임 가져오기
        UserDto.UserResponse userNickname = getUserNickname();
        todoRequestDto.setNickname(userNickname.getNickname());

        Todo todo;

        // 팀유저 ->
        List<BoraTeamUser> teamUsers = boraTeamUserRepository.findBoraTeamUserByBoraTeamTeamName(teamName);
        List<String> collect = teamUsers.stream().map(BoraTeamUser::getTeamMembers)
                .collect(Collectors.toList());

        List<User> collect1 = teamUsers.stream().map(BoraTeamUser::getUser).collect(Collectors.toList());

        if (collect.isEmpty()) {
            todo = todoRequestDto.toEntity(null);
        } else {
            todo = todoRequestDto.toEntity(collect.get(0));
        }

//        // todo 엔티티 저장
//        todo = todoRequestDto.toEntity(nickname);
        // 파일 업로드 연관 Todo

        Long todoId = todoRepository.save(todo).getTodoId();

        if (todoId != null) {
            fileUtil.uploadFiles(multipartFile, FileType.TODO, todoId, null);
        }
//
        // asignee에게 알림 보내기
        if (todoRequestDto.getAssignee() != null) {
            todoNotiService.send(collect1.get(0).getUserId(), todo, "assignee 알림");
        }

        return todoId;
    }

    /**
     * Todo 상세 읽기
     *
     * @param todoId
     * @return
     */
    @Override
    public TodoDto.Response todoRead(Long todoId) {

//        Todo result = repository.getTodo(todoId);

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재 하지 않습니다. " + todoId));

        return new TodoDto.Response(todo);
    }


    /**
     * Todo 수정(파일 업로드 포함)
     *
     * @param todoId
     * @param todoDto
     * @param multipartFile
     */
    @Override
    @Transactional
    public void todoModify(Long todoId, TodoDto.Request todoDto, List<MultipartFile> multipartFile, String teamName) {

        Todo todo = todoRepository.getById(todoId);

        if (todo.getTodoId() != null) {
            fileUtil.updateFiles(multipartFile, FileType.TODO, todo.getTodoId(), null, null);
        }

        // todo 변경 메서드 모음
        changeTodo(todoDto, todo);

        changeAssignee(todo, teamName);
        todoRepository.save(todo);
    }


    // Assignee 변경
    private void changeAssignee(Todo todo, String teamName) {

        // 사용자 닉네임 값 가져오기
//        UserResponseDto userNickname = userRepository.findById(SecurityUtil.getCurrentUserId())
//                .map(UserResponseDto::of).orElseThrow();
//        UserResponseDto userNickname = getUserNickname();
//        String nickname = todoRequestDto.setNickname(userNickname.getNickname());

//        BoraTeamUserDto boraTeamUserDto
        List<BoraTeamUser> teamUsers = boraTeamUserRepository.findBoraTeamUserByBoraTeamTeamName(teamName);
        List<String> collect = teamUsers.stream().map(BoraTeamUser::getTeamMembers)
                .collect(Collectors.toList());

        // 닉네임 가져와서 -> change
        todo.changeAssignee(collect.get(1));
    }


    /**
     * Todo 삭제
     *
     * @param todoId
     */
    @Override
    @Transactional
    public void todoRemove(Long todoId) {

        deleteTodoRelate(todoId);

        todoRepository.deleteById(todoId);
    }


    @Override
    public List<String> findAssignee(Long userId) {
        List<Todo> assgineeNotification = todoRepository.findAssgineeNotification(userId);

        List<String> collect = assgineeNotification.stream().map(Todo::getAssignee).collect(Collectors.toList());
        String single = assgineeNotification.stream().map(Todo::getNickname).findFirst().get();

        String assignee = "";
        for (int i = 0; i < collect.size(); i++) {
            assignee = collect.get(i).toString();
        }
        log.info(assignee);

        return Collections.singletonList(single + "님과" + assignee + "님이 동맹");
    }


    /**
     * Todo 데이터 삭제 시 연관관계 데이터 삭제 메서드
     *
     * @param todoId
     */
    private void deleteTodoRelate(Long todoId) {
        todoReplyRepository.deleteTodoReplyByTodoId(todoId);

        todoLikeRepository.deleteTodoLikeByTodoId(todoId);

        todoNotiRepository.deleteTodoNotificationByTodo(todoId);

        subTaskRepository.subTaskDelete(todoId);

        fileRepository.todoFilesDelete(todoId);
    }

    // Todo 작성자 - 현재 사용자 닉네임
    private UserDto.UserResponse getUserNickname() {
        UserDto.UserResponse userNickname = userRepository.findById(SecurityUtil.getCurrentUserId())
                .map(UserDto.UserResponse::new).orElseThrow();
        return userNickname;
    }

    private void changeTodo(TodoDto.Request todoDto, Todo todo) {
        todo.changeTitle(todoDto.getTitle());

        todo.changeDescription(todoDto.getDescription());

        todo.changeStart(todoDto.getStart());

        todo.changeEnd(todoDto.getEnd());

        todo.changeAssignee(todoDto.getNickname());

        todo.changePriority(todoDto.getPriority() == null ? TodoPriorityType.BASIC : todoDto.getPriority());

        todo.changeDoneTime(todoDto.getTodoType() == TodoType.DONE ? todoDto.getDoneTime() : todo.getModDate());

        changeTodoPoint(todoDto, todo);

        todo.changeTodoType(todoDto.getTodoType());

    }


    // TodoPoint 변경
    private void changeTodoPoint(TodoDto.Request todoDto, Todo todo) {
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
