package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.entity.Project;
import com.coursework.TaskManager.entity.User;
import com.coursework.TaskManager.repository.ProjectRepo;
import com.coursework.TaskManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService implements BaseService<ProjectDTO> {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(ProjectDTO projectDTO) {
        System.out.println("save");
        Project entity = new Project();
        entity.setName(projectDTO.getName());
        entity.setDescription(projectDTO.getDescription());
        System.out.println(projectDTO.getUserId());
        User owner = userRepo.findById(projectDTO.getUserId()).orElse(null);

        if (owner != null) {
            entity.setOwner(owner);
            projectRepo.save(entity);
            System.out.println("not null");
        }
    }

    public ProjectDTO find(ProjectDTO projectDTO) {
        Project entity = projectRepo.findByName(projectDTO.getName(), projectDTO.getUserId())
                .orElse(null);
        if (entity == null) {
            return null;
        }
        projectDTO.setDescription(entity.getDescription());
        return projectDTO;
    }

    public ProjectDTO find(long id) {
        Project entity = projectRepo.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO(
                entity.getProjectId(),
                entity.getName(),
                entity.getDescription(),
                entity.getOwner().getUserId());
        return projectDTO;
    }

    public List<ProjectDTO> findAll(long ownerId) {
        List<Project> projects = projectRepo.findAllByOwnerId(ownerId);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            projectDTOS.add(new ProjectDTO(project.getProjectId(),
                    project.getName(),
                    project.getDescription(),
                    project.getOwner().getUserId()));
        }
        System.out.println("projects " + ownerId);
        System.out.println(projects);
        System.out.println(projectDTOS);
        return projectDTOS;
    }

    public ProjectDTO delete(long id) {
        Project entity = projectRepo.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        projectRepo.delete(entity);
        return new ProjectDTO(entity.getName(),
                entity.getDescription(),
                entity.getOwner().getUserId());
    }

    public ProjectDTO delete(ProjectDTO projectDTO) {
        Project entity = projectRepo.findByName(projectDTO.getName(), projectDTO.getUserId())
                .orElse(null);
        if (entity == null) {
            return null;
        }
        return new ProjectDTO(entity.getName(),
                entity.getDescription(),
                entity.getOwner().getUserId());
    }
}
