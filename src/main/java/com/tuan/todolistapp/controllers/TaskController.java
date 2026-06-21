package com.tuan.todolistapp.controllers;

import com.tuan.todolistapp.dtos.requests.TaskCreateRequest;
import com.tuan.todolistapp.dtos.requests.TaskUpdateRequest;
import com.tuan.todolistapp.dtos.responses.PageResponse;
import com.tuan.todolistapp.models.enums.ETaskStatus;
import com.tuan.todolistapp.services.interfaces.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final ITaskService taskService;

    @GetMapping("")
    public ResponseEntity<PageResponse> getAllTasks(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) ETaskStatus status,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(taskService.getAllTasks(title, status, page, size));
    }

    @PostMapping("")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskCreateRequest request) {
        taskService.createTask(request);
        return ResponseEntity.status(201).body("Task created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        taskService.updateTask(id, request);
        return ResponseEntity.ok("Task updated successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        taskService.changeStatus(id);
        return ResponseEntity.ok("Task status updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
