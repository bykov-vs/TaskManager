package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.dto.TaskDTO;
import com.coursework.TaskManager.entity.Project;
import com.coursework.TaskManager.entity.Task;
import com.coursework.TaskManager.repository.ProjectRepo;
import com.coursework.TaskManager.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements BaseService<TaskDTO> {
    @Autowired private TaskRepo taskRepo;
    @Autowired private ProjectRepo projectRepo;

    public void save(TaskDTO taskDTO) {
        Task entity = new Task();
        entity.setName(taskDTO.getName());
        entity.setDescription(taskDTO.getDescription());
        entity.setStatus(taskDTO.getStatus());
        System.out.println(taskDTO);
        Project project = projectRepo.findById(taskDTO.getProjectId()).orElse(null);
        if (project != null){
            entity.setProject(project);
            taskRepo.save(entity);
        }
    }

    public void saveAll(List<TaskDTO> taskDTOS){
        long projectId = taskDTOS.get(0).getProjectId();
        Project project = projectRepo.findById(projectId).orElse(null);

        if (project != null){
            for (Task task : project.getTasks()) {
                delete(task.getTaskId());
            }
        }
        for (TaskDTO taskDTO : taskDTOS) {
            save(taskDTO);
        }
    }

    public TaskDTO find(TaskDTO taskDTO) {
        Task task = taskRepo.findByName(taskDTO.getName(), taskDTO.getProjectId()).orElse(null);
        if (task == null){
            return null;
        }
        return new TaskDTO(task.getName(), task.getDescription());
    }

    public TaskDTO find(long id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null){
            return null;
        }
        return new TaskDTO(task.getName(), task.getDescription());
    }

    public List<TaskDTO> findAll(long projectId){
        List<Task> tasks = taskRepo.findAll(projectId);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskDTOS.add(new TaskDTO(task.getName(), task.getDescription(), task.getStatus(), projectId));
        }
        return taskDTOS;
    }

    public TaskDTO delete(long id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task == null){
            return null;
        }
        taskRepo.delete(task);
        return new TaskDTO(task.getName(), task.getDescription());
    }

    public TaskDTO delete(TaskDTO taskDTO) {
        Task task = taskRepo.findByName(taskDTO.getName(), taskDTO.getProjectId()).orElse(null);
        if (task == null){
            return null;
        }
        taskRepo.delete(task);
        return new TaskDTO(task.getName(), task.getDescription());
    }
    public void deleteAll(Project project){
        taskRepo.deleteAllByProjectId(project.getProjectId());
    }
}
