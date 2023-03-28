package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.entity.User;
import com.coursework.TaskManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements BaseService<UserDTO> {
    @Autowired private UserRepo repo;

    public void save(UserDTO userDTO) {
        User entity = new User();
        entity.setUsername(userDTO.getUsername());
        repo.save(entity);
    }

    public UserDTO find(UserDTO userDTO) {
        User user = repo.findByUsername(userDTO.getUsername()).orElse(null);
        if (user != null){
            return new UserDTO(user.getUsername());
        }
        return null;
    }

    public UserDTO find(long id) {
        User user = repo.findById(id).orElse(null);
        if (user != null){
            return new UserDTO(user.getUsername());
        }
        return null;
    }

    public List<UserDTO> findAll() {
        List<User> users = repo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); i++) {
            userDTOS.set(i, new UserDTO(users.get(i).getUsername()));
        }
        return userDTOS;
    }

    public UserDTO delete(long id) {
        User user = repo.findById(id).orElse(null);
        if (user == null){
            return null;
        }
        repo.delete(user);
        return new UserDTO(user.getUsername());
    }

    public UserDTO delete(UserDTO userDTO) {
        User user = repo.findByUsername(userDTO.getUsername()).orElse(null);
        if (user == null){
            return null;
        }
        repo.delete(user);
        return new UserDTO(user.getUsername());
    }
}
