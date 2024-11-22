package org.example.testosteron.service.mapper;

import org.example.testosteron.entity.task.Task;
import org.example.testosteron.enums.TaskStatus;
import org.example.testosteron.service.dto.task.TaskRequestDto;
import org.example.testosteron.service.dto.task.TaskResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setTaskStatus(Enum.valueOf(TaskStatus.class, taskRequestDto.getTaskStatus()));
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return task;
    }
}
