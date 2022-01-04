package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Log4j2
@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository subTaskRepository;

    @Override
    public Long save(SubTaskDto subTaskDto) {

        SubTask subTask = dtoToEntity(subTaskDto);

        subTaskRepository.save(subTask);

        return subTaskDto.getSubTaskId();
    }

    @Override
    public List<SubTaskDto> getList(Long todoId) {

        List<SubTask> result = subTaskRepository.getSubTasksByTodoOrderByRegDate(Todo.builder().todoId(todoId).build());

        return result.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void remove(Long subTaskId) {

        subTaskRepository.deleteById(subTaskId);
    }

    @Transactional
    @Override
    public void modify(Long subTaskId, SubTaskDto subTaskDto) {
        SubTask subTask = subTaskRepository.getById(subTaskId);
        subTask.changeTitle(subTaskDto.getTitle());
        subTask.changeStart(subTaskDto.getStart());
        subTask.changeEnd(subTaskDto.getEnd());
        subTask.changeAssignee(subTaskDto.getAssignee());

        subTaskRepository.save(subTask);
    }

}
