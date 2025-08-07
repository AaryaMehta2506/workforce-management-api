package com.yourcompany.workforcemgmt.model;

import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    private Long id;
    private String title;
    private Status status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Staff assignedTo;
    private Priority priority;
    private List<String> comments = new ArrayList<>();
    private List<String> history = new ArrayList<>();
}
