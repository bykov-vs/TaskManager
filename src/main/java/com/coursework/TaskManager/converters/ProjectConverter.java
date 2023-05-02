package com.coursework.TaskManager.converters;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.entity.Project;

public class ProjectConverter {
    public static Project convert(ProjectDTO projectDTO){
        Project project = new Project();
        project.setProjectId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setTasks(projectDTO.getTasks());
        return project;
    }

    public static ProjectDTO convert(Project project){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(project.getName());
        projectDTO.setTasks(projectDTO.getTasks());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setUserId(project.getOwner().getUserId());
        projectDTO.setId(project.getProjectId());
        return projectDTO;
    }
}
