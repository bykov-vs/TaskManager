package com.coursework.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {
    private String name;
    private String description;
    private long projectId;

    public TaskDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
