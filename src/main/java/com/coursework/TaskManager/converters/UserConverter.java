package com.coursework.TaskManager.converters;

import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.entity.User;

public class UserConverter {
    public static User convert(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setUserId(userDTO.getId());
        return user;
    }

    public static UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setId(user.getUserId());
        return userDTO;
    }
}
