package com.coursework.TaskManager.endpoint;

import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.dto.UserDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> findProject(@PathVariable long id){
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @GetMapping("/global-all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/saveRequest")
    public ResponseEntity<?> saveParticipant(@RequestParam Long projectId, @RequestParam Long userId){
        try {
            service.saveParticipant(projectId, userId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/acceptParticipant")
    public ResponseEntity<?> acceptParticipant(@RequestParam Long projectId, @RequestParam Long userId){
        try {
            service.acceptParticipant(projectId, userId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/rejectParticipant")
    public ResponseEntity<?> rejectParticipant(@RequestParam Long projectId, @RequestParam Long userId){
        try {
            service.rejectParticipant(projectId, userId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
