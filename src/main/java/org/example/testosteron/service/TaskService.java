package org.example.testosteron.service;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.testosteron.entity.task.Task;
import org.example.testosteron.enums.ProcessingStatus;
import org.example.testosteron.enums.TaskStatus;
import org.example.testosteron.repository.TaskRepository;
import org.example.testosteron.service.dto.task.TaskRequestDto;
import org.example.testosteron.service.dto.task.TaskResponseDto;
import org.example.testosteron.service.mapper.TaskMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    //TODO: make business data validation
    public TaskResponseDto validateFields(TaskRequestDto taskRequestDto) {
        return new TaskResponseDto();
    }

    public TaskResponseDto createTask(TaskRequestDto requestDto){
        TaskResponseDto response = new TaskResponseDto();
        if (StringUtils.isBlank(requestDto.getTitle())) {
            response.getErrors().add("title - обязательное поле для создания задачи");
            response.setStatus(ProcessingStatus.ERROR);
            return response;
        }
        taskRepository.save(taskMapper.toEntity(requestDto));
        response.setStatus(ProcessingStatus.SUCCESS);
        return response;
    }

    public TaskResponseDto getTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return createResponse(task);
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, TaskRequestDto requestDto) {
        Task task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        task.setTitle(requestDto.getTitle());
        if (requestDto.getDescription() != null){
            task.setDescription(requestDto.getDescription());
        }
        if (requestDto.getTaskStatus() != null) {
            task.setTaskStatus(TaskStatus.valueOf(requestDto.getTaskStatus()));
        }
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);

        return createResponse(null);
    }

    public TaskResponseDto deleteTask(Long id){
        taskRepository.deleteById(id);
        return createResponse(null);
    }

    private TaskResponseDto createResponse(Task task) {
        TaskResponseDto response = new TaskResponseDto();
        response.setTask(task);
        response.setStatus(ProcessingStatus.SUCCESS);
        return response;
    }
}
