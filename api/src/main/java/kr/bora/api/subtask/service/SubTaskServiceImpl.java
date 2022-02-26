package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final TodoRepository todoRepository;

    /**
     * SubTask 등록
     * @param subTaskDto
     * @param todoId
     * @return
     */
    @Override
    @Transactional
    public Long subTaskSave(SubTaskDto subTaskDto, Long todoId) {

        SubTask subTask = dtoToEntity(subTaskDto);
        subTaskRepository.save(subTask);

        return subTaskDto.getSubTaskId();
    }

    /**
     * SubTask 리스트
     * @param todoId
     * @return
     */
    @Override
    public List<SubTaskDto> subTaskList(Long todoId) {
        List<SubTask> result = subTaskRepository.getSubTasksByTodoOrderByRegDate(Todo.builder().todoId(todoId).build());

        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    /**
     * SubTask 수정
     * @param subTaskId
     * @param subTaskDto
     */
    @Transactional
    @Override
    public void subTaskModify(Long subTaskId, SubTaskDto subTaskDto) {
        SubTask subTask = subTaskRepository.getById(subTaskId);
        subTask.changeTitle(subTaskDto.getTitle());
        subTask.changeStart(subTaskDto.getStart());
        subTask.changeEnd(subTaskDto.getEnd());
        subTask.changeAssignee(subTaskDto.getAssignee());
        subTask.changeDone(subTaskDto.getDone());
        subTaskRepository.save(subTask);
    }

    /**
     * SubTask 삭제
     * @param subTaskId
     */
    @Override
    public void subTaskRemove(Long subTaskId) {

        subTaskRepository.deleteById(subTaskId);
    }


}
