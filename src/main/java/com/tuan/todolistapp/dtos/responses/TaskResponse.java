package com.tuan.todolistapp.dtos.responses;

import com.tuan.todolistapp.models.enums.ETaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private ETaskStatus status;
}
