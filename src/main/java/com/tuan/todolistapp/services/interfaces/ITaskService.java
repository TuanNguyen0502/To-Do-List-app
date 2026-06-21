package com.tuan.todolistapp.services.interfaces;

import com.tuan.todolistapp.dtos.requests.TaskCreateRequest;
import com.tuan.todolistapp.dtos.requests.TaskUpdateRequest;
import com.tuan.todolistapp.dtos.responses.PageResponse;
import com.tuan.todolistapp.models.enums.ETaskStatus;

public interface ITaskService {
    PageResponse getAllTasks(String title, ETaskStatus status, int page, int size, String sortBy);

    void createTask(TaskCreateRequest request);

    void updateTask(Long id, TaskUpdateRequest request);

    void changeStatus(Long id);

    void deleteTask(Long id);
}
