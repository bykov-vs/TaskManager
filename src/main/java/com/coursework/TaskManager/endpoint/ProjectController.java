package com.coursework.TaskManager.endpoint;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.repository.ProjectRepo;
import com.coursework.TaskManager.service.ProjectService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired private ProjectService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveProject(@RequestBody ProjectDTO projectDTO){
        try {
            service.save(projectDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllProjects(@RequestParam("id") long ownerId){
        return new ResponseEntity<>(service.findAll(ownerId), HttpStatus.OK);
    }
}
