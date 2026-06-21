package com.tuan.todolistapp.services.impl;

import com.tuan.todolistapp.dtos.requests.TaskCreateRequest;
import com.tuan.todolistapp.dtos.requests.TaskUpdateRequest;
import com.tuan.todolistapp.dtos.responses.PageResponse;
import com.tuan.todolistapp.dtos.responses.TaskResponse;
import com.tuan.todolistapp.exceptions.AppException;
import com.tuan.todolistapp.models.entites.Task;
import com.tuan.todolistapp.models.enums.ETaskStatus;
import com.tuan.todolistapp.models.enums.ErrorCode;
import com.tuan.todolistapp.repositories.TaskRepository;
import com.tuan.todolistapp.repositories.specifications.TaskSpecification;
import com.tuan.todolistapp.services.interfaces.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private final TaskRepository taskRepository;

    @Override
    public PageResponse getAllTasks(String title, ETaskStatus status, int page, int size, String sortBy) {
        Specification<Task> spec = (_, _, cb) -> cb.conjunction();
        if (title != null && !title.isBlank()) {
            spec = spec.and(TaskSpecification.titleContains(title));
        }
        if (status != null) {
            spec = spec.and(TaskSpecification.statusEquals(status));
        }

        Sort sort = Sort.by(Sort.Direction.fromString("DESC"), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TaskResponse> taskResponses = taskRepository.findAll(spec, pageable)
                .map(this::toTaskResponse);
        return toPageResponse(taskResponses);
    }

    @Override
    public void createTask(TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(ETaskStatus.PENDING);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "Task not found with id: " + id));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        taskRepository.save(task);
    }

    @Override
    public void changeStatus(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "Task not found with id: " + id));
        task.setStatus(task.getStatus() == ETaskStatus.PENDING ? ETaskStatus.COMPLETED : ETaskStatus.PENDING);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "Task not found with id: " + id));
        taskRepository.delete(task);
    }

    private TaskResponse toTaskResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    private <T> PageResponse toPageResponse(Page<T> page, Object content) {
        PageResponse.Meta meta = new PageResponse.Meta();
        meta.setPage(page.getNumber());
        meta.setPageSize(page.getSize());
        meta.setPages(page.getTotalPages());
        meta.setTotal(page.getTotalElements());

        return PageResponse.builder()
                .meta(meta)
                .result(content)
                .build();
    }

    private <T> PageResponse toPageResponse(Page<T> page) {
        return toPageResponse(page, page.getContent());
    }
}
