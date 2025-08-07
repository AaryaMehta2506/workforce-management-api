package com.yourcompany.workforcemgmt.service;

import com.yourcompany.workforcemgmt.dto.CreateTaskRequest;
import com.yourcompany.workforcemgmt.dto.TaskDto;
import com.yourcompany.workforcemgmt.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final Map<Long, Task> taskStorage = new HashMap<>();
    private final Map<Long, Staff> staffStorage = new HashMap<>();
    private final AtomicLong taskIdCounter = new AtomicLong(1);
    private final AtomicLong staffIdCounter = new AtomicLong(1);

    public TaskDto createTask(CreateTaskRequest request) {
        Staff staff = staffStorage.computeIfAbsent(request.getStaffId(), id -> 
            Staff.builder().id(id).name("Staff #" + id).build());

        Task task = Task.builder()
                .id(taskIdCounter.getAndIncrement())
                .title(request.getTitle())
                .status(Status.ACTIVE)
                .startDate(request.getStartDate())
                .dueDate(request.getDueDate())
                .assignedTo(staff)
                .priority(Priority.valueOf(request.getPriority().toUpperCase()))
                .comments(new ArrayList<>())
                .history(new ArrayList<>())
                .build();

        task.getHistory().add("Task created and assigned to " + staff.getName());

        taskStorage.put(task.getId(), task);

        return toDto(task);
    }

    public List<TaskDto> getTasksForDateRange(LocalDate start, LocalDate end) {
        return taskStorage.values().stream()
            .filter(t -> t.getStatus() != Status.CANCELLED)
            .filter(t -> (t.getStartDate().compareTo(start) >= 0 && t.getStartDate().compareTo(end) <= 0)
                      || (t.getStartDate().isBefore(start) && t.getStatus() == Status.ACTIVE))
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public List<TaskDto> getTasksByPriority(Priority priority) {
        return taskStorage.values().stream()
            .filter(t -> t.getPriority() == priority)
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public TaskDto updatePriority(Long taskId, Priority newPriority) {
        Task task = taskStorage.get(taskId);
        if (task != null) {
            task.setPriority(newPriority);
            task.getHistory().add("Priority changed to " + newPriority);
        }
        return toDto(task);
    }

    public TaskDto reassignTask(Long taskId, Long newStaffId) {
        Task task = taskStorage.get(taskId);
        if (task != null) {
            task.setStatus(Status.CANCELLED);
            task.getHistory().add("Task cancelled for reassignment");

            CreateTaskRequest newReq = new CreateTaskRequest(task.getTitle(), newStaffId, task.getStartDate(), task.getDueDate(), task.getPriority().name());
            return createTask(newReq);
        }
        return null;
    }

    public void addComment(Long taskId, String comment) {
        Task task = taskStorage.get(taskId);
        if (task != null) {
            task.getComments().add(comment);
            task.getHistory().add("Comment added: " + comment);
        }
    }

    public Task getTaskDetails(Long taskId) {
        return taskStorage.get(taskId);
    }

    private TaskDto toDto(Task task) {
        return TaskDto.builder()
            .id(task.getId())
            .title(task.getTitle())
            .status(task.getStatus().name())
            .startDate(task.getStartDate())
            .dueDate(task.getDueDate())
            .staffName(task.getAssignedTo().getName())
            .priority(task.getPriority().name())
            .build();
    }
}
