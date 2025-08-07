package com.yourcompany.workforcemgmt.controller;

import com.yourcompany.workforcemgmt.dto.CreateTaskRequest;
import com.yourcompany.workforcemgmt.dto.TaskDto;
import com.yourcompany.workforcemgmt.model.Priority;
import com.yourcompany.workforcemgmt.model.Task;
import com.yourcompany.workforcemgmt.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto createTask(@RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping("/range")
    public List<TaskDto> getTasksForDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return taskService.getTasksForDateRange(start, end);
    }

    @PostMapping("/{id}/priority")
    public TaskDto updatePriority(@PathVariable Long id, @RequestParam Priority priority) {
        return taskService.updatePriority(id, priority);
    }

    @GetMapping("/priority/{priority}")
    public List<TaskDto> getTasksByPriority(@PathVariable Priority priority) {
        return taskService.getTasksByPriority(priority);
    }

    @PostMapping("/{id}/reassign")
    public TaskDto reassignTask(@PathVariable Long id, @RequestParam Long staffId) {
        return taskService.reassignTask(id, staffId);
    }

    @PostMapping("/{id}/comment")
    public void addComment(@PathVariable Long id, @RequestBody String comment) {
        taskService.addComment(id, comment);
    }

    @GetMapping("/{id}")
    public Task getTaskDetails(@PathVariable Long id) {
        return taskService.getTaskDetails(id);
    }
