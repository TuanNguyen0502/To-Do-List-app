package com.tuan.todolistapp.dtos.requests;

import com.tuan.todolistapp.commons.constants.MessageConstant;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequest {
    @NotBlank(message = MessageConstant.TASK_TITLE_NOT_BLANK)
    @Max(value = 255, message = MessageConstant.TASK_TITLE_MAX)
    private String title;

    @NotBlank(message = MessageConstant.TASK_DESCRIPTION_NOT_BLANK)
    private String description;
}
