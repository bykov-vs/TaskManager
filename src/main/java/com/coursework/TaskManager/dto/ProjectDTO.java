package com.coursework.TaskManager.dto;

import com.coursework.TaskManager.entity.Project;
import com.coursework.TaskManager.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    private long userId;
    private List<UserDTO> participants;
    private List<UserDTO> requests;
    private List<Task> tasks = new ArrayList<>();

    public ProjectDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProjectDTO(long id, String name, String description, long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public ProjectDTO(String name, String description, long userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
