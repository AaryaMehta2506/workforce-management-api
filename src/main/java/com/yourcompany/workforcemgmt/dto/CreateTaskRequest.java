package com.yourcompany.workforcemgmt.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequest {
    private String title;
    private Long staffId;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String priority;
}
