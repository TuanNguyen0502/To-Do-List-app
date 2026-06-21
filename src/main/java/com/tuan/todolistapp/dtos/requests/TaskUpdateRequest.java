package com.tuan.todolistapp.dtos.requests;

import com.tuan.todolistapp.commons.constants.MessageConstant;
import com.tuan.todolistapp.models.enums.ETaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequest {
    @NotBlank(message = MessageConstant.TASK_TITLE_NOT_BLANK)
    @Length(max = 255, message = MessageConstant.TASK_TITLE_MAX)
    private String title;

    @NotBlank(message = MessageConstant.TASK_DESCRIPTION_NOT_BLANK)
    private String description;

    @NotNull(message = MessageConstant.TASK_STATUS_NOT_NULL)
    private ETaskStatus status;
}
