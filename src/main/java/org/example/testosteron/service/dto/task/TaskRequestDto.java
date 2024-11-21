package org.example.testosteron.service.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import org.example.testosteron.enums.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TaskRequestDto {

    @ApiModelProperty("Заголовок задачи (обязательное поле, до 100 символов)")
    @Size(max = 100)
    @NotNull
    private String title;

    @ApiModelProperty("Описание задачи (необязательное поле)")
    private String description;

    @ApiModelProperty("Статус задачи (PENDING, IN_PROGRESS, COMPLETED)")
    private String status;
}
