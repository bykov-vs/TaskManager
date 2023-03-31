package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.entity.Project;
import com.coursework.TaskManager.entity.User;
import com.coursework.TaskManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements BaseService<UserDTO> {
    @Autowired private UserRepo repo;
    @Autowired private ProjectService projectService;

    public void save(UserDTO userDTO) {
        User entity = new User();
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        repo.save(entity);
    }

    public UserDTO find(UserDTO userDTO) {
        User user = repo.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()).orElse(null);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        if (user != null){
            for (Project project : user.getProjects()) {
                projectDTOS.add(new ProjectDTO(project.getProjectId(),
                        project.getName(),
                        project.getDescription(),
                        project.getOwner().getUserId()));
            }
            UserDTO dto = new UserDTO(user.getUserId(), user.getUsername(), user.getPassword(), projectDTOS);
            dto.setProjects(projectService.findAll(user.getUserId()));
            return dto;
        }
        return null;
    }

    public UserDTO find(long id) {
        User user = repo.findById(id).orElse(null);
        if (user != null){
            return new UserDTO(user.getUserId(),user.getUsername(), user.getPassword());
        }
        return null;
    }

    public List<UserDTO> findAll() {
        List<User> users = repo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            userDTOS.set(i, new UserDTO(users.get(i).getUsername(), users.get(i).getPassword()));
        }
        return userDTOS;
    }

    public UserDTO delete(long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null){
            return null;
        }
        repo.delete(user);
        return new UserDTO(user.getUsername(), user.getPassword());
    }

    public UserDTO delete(UserDTO userDTO) {
        User user = repo.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()).orElse(null);
        if (user == null){
            return null;
        }
        repo.delete(user);
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
