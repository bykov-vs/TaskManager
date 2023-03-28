package com.coursework.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDTO {
    private String name;
    private String description;
    private long userId;

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
