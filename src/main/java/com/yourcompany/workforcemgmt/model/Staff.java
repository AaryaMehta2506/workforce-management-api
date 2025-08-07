package com.yourcompany.workforcemgmt.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
    private Long id;
    private String name;
}
