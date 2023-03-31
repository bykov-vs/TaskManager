package com.coursework.TaskManager.endpoint;

import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping("/")
    public ResponseEntity<?> authorize(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        UserDTO dto = service.authorize(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
