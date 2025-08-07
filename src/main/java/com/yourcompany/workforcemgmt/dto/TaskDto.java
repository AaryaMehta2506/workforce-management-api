package com.yourcompany.workforcemgmt.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String staffName;
    private String priority;
}
