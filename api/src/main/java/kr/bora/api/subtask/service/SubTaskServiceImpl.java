package kr.bora.api.subtask.service;

import kr.bora.api.subtask.domain.SubTask;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.repository.SubTaskRepository;
import kr.bora.api.todo.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository subTaskRepository;

    @Transactional
    @Override
    public Long save(SubTaskDto subTaskDto) {

        SubTask subTaskSave = dtoToEntity(subTaskDto);

        subTaskRepository.save(subTaskSave);

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

    @Override
    public void modify(SubTaskDto subTaskDto) {
        SubTask subTask = dtoToEntity(subTaskDto);

        subTaskRepository.save(subTask);
    }

}
