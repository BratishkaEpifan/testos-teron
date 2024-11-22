package org.example.testosteron;

import org.example.testosteron.enums.ProcessingStatus;
import org.example.testosteron.resource.TaskResource;
import org.example.testosteron.service.TaskService;
import org.example.testosteron.service.dto.task.TaskRequestDto;
import org.example.testosteron.service.dto.task.TaskResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskResource.class)
public class TaskResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void testPostTaskWithInvalidData() throws Exception {
        TaskResponseDto errorResponse = new TaskResponseDto();
        errorResponse.setStatus(ProcessingStatus.ERROR);
        errorResponse.getErrors().add("title - обязательное поле для создания задачи");
        when(taskService.createTask(any(TaskRequestDto.class))).thenReturn(errorResponse);

        String invalidRequestBody = "{\n" +
                "    \"title\": \" \",\n" +
                "    \"description\": \"Тестовое описание\",\n" +
                "    \"taskStatus\": \"COMPLETED\"\n" +
                "}";

        mockMvc.perform(post("/open-api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testPostTask() throws Exception {
        TaskResponseDto successResponse = new TaskResponseDto();
        successResponse.setStatus(ProcessingStatus.SUCCESS);
        when(taskService.createTask(any(TaskRequestDto.class))).thenReturn(successResponse);


        String requestBody = "{\n" +
                "    \"title\": \"Тестовый заголовок\",\n" +
                "    \"description\": \"Тестовое описание\",\n" +
                "    \"taskStatus\": \"COMPLETED\"\n" +
                "}";

        mockMvc.perform(post("/open-api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
