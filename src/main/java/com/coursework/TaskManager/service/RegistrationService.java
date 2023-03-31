package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserService userService;

    public boolean register(UserDTO userDTO){
        UserDTO user = userService.find(userDTO);
        if (user != null){
            return false;
        }
        userService.save(userDTO);
        return true;
    }
}
