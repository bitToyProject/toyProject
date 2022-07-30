package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.domain.SubtaskType;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.repository.SubTaskReplyRepository;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository subTaskRepository;

    private final SubTaskReplyRepository subTaskReplyRepository;
    /**
     * SubTask 등록
     *
     * @param subTaskDto
     * @param todoId
     * @return
     */
    @Override
    @Transactional
    public Long subTaskSave(SubTaskDto.Request subTaskDto, Long todoId) {
        SubTask subTask = subTaskDto.toEntity(todoId);
        subTaskRepository.save(subTask);

        return subTask.getSubTaskId();
    }

    /**
     * SubTask 읽기
     * @param subTaskId
     * @return
     */
    @Override
    public SubTaskDto.Response subTaskRead(Long subTaskId) {

        SubTask subTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재 하지 않습니다. " + subTaskId));

        return new SubTaskDto.Response(subTask);
    }

    /**
     * SubTask 리스트
     *
     * @param todoId
     * @return
     */
    @Override
    public List<SubTaskDto.Response> subTaskList(Long todoId) {
//        List<SubTask> result = subtaskListOrderByRegDate(todoId);
        List<SubTask> subTasksList = subTaskRepository.getSubTasksByTodoOrderByRegDateDesc(Todo.builder().todoId(todoId).build());

        return subTasksList.stream().map(SubTaskDto.Response::new).collect(Collectors.toList());
    }

    /**
     * SubTask 수정
     *
     * @param subTaskId
     * @param subTaskDto
     */
    @Override
    @Transactional
    public void subTaskModify(Long subTaskId, SubTaskDto.Request subTaskDto) {
        SubTask subTask = subTaskRepository.getById(subTaskId);
        changeSubtask(subTaskDto, subTask);
        subTaskRepository.save(subTask);
    }

    /**
     * SubTask 삭제
     *
     * @param subTaskId
     */
    @Override
    @Transactional
    public void subTaskRemove(Long subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }

    // SubTask 수정 메서드
    private void changeSubtask(SubTaskDto.Request subTaskDto, SubTask subTask) {
        subTask.changeTitle(subTaskDto.getTitle());

        subTask.changeStart(subTaskDto.getStart());

        subTask.changeEnd(subTaskDto.getEnd());

        subTask.changeAssignee(subTaskDto.getAssignee());

        subTask.changeDoneTime(subTaskDto.getSubtaskType() == SubtaskType.DONE ? subTaskDto.getDoneTime() : subTaskDto.getModDate());

        subTask.changeSubTaskType(subTaskDto.getSubtaskType());

        changeSubtaskPoint(subTaskDto, subTask);
    }

    // SubTask Point 수정 메서드 분리
    private void changeSubtaskPoint(SubTaskDto.Request subTaskDto, SubTask subTask) {
        if (subTaskDto.getSubtaskType() == SubtaskType.DONE && subTask.getPoint() == 0) {
            subTask.changePoint(subTask.getPoint() + 10);
        }

        if (!(subTaskDto.getSubtaskType() == SubtaskType.DONE) && subTask.getPoint() == 10) {
            subTask.changePoint(subTask.getPoint() - 10);
        }else{
            subTask.changePoint(subTask.getPoint());
        }
    }



}
