package com.coursework.TaskManager.endpoint;

import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.response.BaseResponse;
import com.coursework.TaskManager.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        boolean register = service.register(userDTO);
        BaseResponse response = new BaseResponse("successful");
        if (register){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.status = "failed";
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
