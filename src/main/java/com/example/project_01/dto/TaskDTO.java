package com.example.project_01.dto;

import com.example.project_01.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
}
