package org.example.testosteron.resource;

import lombok.RequiredArgsConstructor;
import org.example.testosteron.service.TaskService;
import org.example.testosteron.service.dto.TaskRequestDto;
import org.example.testosteron.service.dto.TaskResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController("/open-api")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto) {

    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {

    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> updateTaskById(@PathVariable Long id, @RequestBody @Valid TaskRequestDto taskRequestDto) {}() {

    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<TaskResponseDto> deleteMapping() {

    }
}
