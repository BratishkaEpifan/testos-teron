package org.example.testosteron.service.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.testosteron.entity.task.Task;
import org.example.testosteron.enums.ProcessingStatus;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Setter
@Getter
public class TaskResponseDto {

    @Schema(description = "Статус обработки запроса")
    private ProcessingStatus status;

    @Schema(description = "Задача")
    private Task task;

    @Schema(description = "Список найденных ошибок")
    private List<String> errors = new LinkedList<>();
}
