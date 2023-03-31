package com.coursework.TaskManager.endpoint;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.dto.TaskDTO;
import com.coursework.TaskManager.service.ProjectService;
import com.coursework.TaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveTask(@RequestBody TaskDTO taskDTO){
        try {
            service.save(taskDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAllTasks(@RequestBody List<TaskDTO> taskDTOS){
        try {
            service.saveAll(taskDTOS);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllTasks(@RequestParam("id") long projectId){
        return new ResponseEntity<>(service.findAll(projectId), HttpStatus.OK);
    }
}
