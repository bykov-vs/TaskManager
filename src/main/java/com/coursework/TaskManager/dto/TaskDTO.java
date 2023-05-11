package com.coursework.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String name;
    private String description;
    private String status;
    private long projectId;
    private long performerId;

    public TaskDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
