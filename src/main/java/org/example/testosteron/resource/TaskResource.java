package org.example.testosteron.resource;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.testosteron.enums.ProcessingStatus;
import org.example.testosteron.service.TaskService;
import org.example.testosteron.service.dto.task.TaskRequestDto;
import org.example.testosteron.service.dto.task.TaskResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.function.Function;

//TODO: change to /api

@RequiredArgsConstructor
@RestController
@RequestMapping(("/open-api"))
public class TaskResource {

    private final TaskService taskService;

    @PostMapping("/tasks")
    ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {
        return executeTaskOperation(taskService::createTask, taskRequestDto);
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        return executeTaskOperation(taskService::getTask, id);
    }

    @PatchMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequestDto taskRequestDto) {
        return executeTaskOperation(
                requestDto -> taskService.updateTask(id, requestDto),
                taskRequestDto
        );
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> deleteTask(@PathVariable Long id) {
        return executeTaskOperation(taskService::deleteTask, id);
    }

    private <T> ResponseEntity<TaskResponseDto> executeTaskOperation(Function<T, TaskResponseDto> function, T input) {
        TaskResponseDto response = new TaskResponseDto();
        try {
            response = function.apply(input);
            if (response.getStatus().equals(ProcessingStatus.SUCCESS)) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception ex) {
            response.getErrors().add(ex.getMessage());
            response.setStatus(ProcessingStatus.ERROR);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
