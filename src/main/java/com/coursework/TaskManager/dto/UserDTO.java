package com.coursework.TaskManager.dto;

import com.coursework.TaskManager.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String password;

    private List<ProjectDTO> projects = new ArrayList<>();

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
