package org.example.testosteron.service.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;


@Getter
public class TaskRequestDto {

    @Schema(description = "Заголовок задачи (условно-обязательное поле (обязательное при создании), до 100 символов)")
    @Size(max = 100)
    private String title;

    @Schema(description = "Описание задачи (необязательное поле)")
    private String description;

    @Schema(description = "Статус задачи (PENDING, IN_PROGRESS, COMPLETED)")
    private String taskStatus;
}
