package com.coursework.TaskManager.service;

import com.coursework.TaskManager.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserService userService;

    public UserDTO authorize(UserDTO userDTO){
        UserDTO dto = userService.find(userDTO);
        return dto;
    }
}
