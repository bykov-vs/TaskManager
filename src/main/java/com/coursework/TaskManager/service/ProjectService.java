package com.coursework.TaskManager.service;

import com.coursework.TaskManager.converters.ProjectConverter;
import com.coursework.TaskManager.converters.UserConverter;
import com.coursework.TaskManager.dto.ProjectDTO;
import com.coursework.TaskManager.dto.UserDTO;
import com.coursework.TaskManager.entity.Participant;
import com.coursework.TaskManager.entity.Project;
import com.coursework.TaskManager.entity.User;
import com.coursework.TaskManager.entity.additionally.ParticipantKey;
import com.coursework.TaskManager.repository.ParticipantRepo;
import com.coursework.TaskManager.repository.ProjectRepo;
import com.coursework.TaskManager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService implements BaseService<ProjectDTO> {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ParticipantRepo participantRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(ProjectDTO projectDTO) {
        Project entity = ProjectConverter.convert(projectDTO);
        System.out.println(projectDTO.getUserId());
        User owner = userRepo.findById(projectDTO.getUserId()).orElse(null);

        if (owner != null) {
            entity.setOwner(owner);
            projectRepo.save(entity);
        }
    }

    public void saveParticipant(Long projectId, Long userId) {
        Participant exists = participantRepo.findByIds(projectId, userId).orElse(null);
        if (exists != null){
            return;
        }
        Project project = projectRepo.findById(projectId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);
        if (user == null || project == null){
            return;
        }
        Participant participant = new Participant();
        participant.setProject(project);
        participant.setUser(user);
        participant.setStatus(false);
        participant.setId(new ParticipantKey(projectId, userId));
        participantRepo.save(participant);
    }

    public ProjectDTO find(ProjectDTO projectDTO) {
        Project entity = projectRepo.findByName(projectDTO.getName(), projectDTO.getUserId())
                .orElse(null);
        if (entity == null) {
            return null;
        }
        projectDTO = ProjectConverter.convert(entity);
        projectDTO.setParticipants(getParticipants(entity));
        projectDTO.setRequests(getRequests(entity));
        return projectDTO;
    }

    public ProjectDTO find(long id) {
        Project entity = projectRepo.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        ProjectDTO dto = ProjectConverter.convert(entity);
        dto.setRequests(getRequests(entity));
        dto.setParticipants(getParticipants(entity));
        return dto;
    }

    public List<ProjectDTO> findAll(long ownerId) {
        List<Project> projects = projectRepo.findAllByOwnerId(ownerId);
        List<Project> projectsParticipant = projectRepo.findAllByParticipantsId(ownerId);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            ProjectDTO dto = ProjectConverter.convert(project);
            dto.setParticipants(getParticipants(project));
            dto.setRequests(getRequests(project));
            projectDTOS.add(dto);
        }
        for (int i = 0; i < projectsParticipant.size(); i++) {
            Project project = projectsParticipant.get(i);
            ProjectDTO dto = ProjectConverter.convert(project);
            dto.setParticipants(getParticipants(project));
            dto.setRequests(getRequests(project));
            projectDTOS.add(ProjectConverter.convert(project));
        }
        return projectDTOS;
    }

    public List<ProjectDTO> findAll() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            projectDTOS.add(ProjectConverter.convert(project));
        }
        return projectDTOS;
    }

    public ProjectDTO delete(long id) {
        Project entity = projectRepo.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        projectRepo.delete(entity);
        return ProjectConverter.convert(entity);
    }

    public ProjectDTO delete(ProjectDTO projectDTO) {
        Project entity = projectRepo.findByName(projectDTO.getName(), projectDTO.getUserId())
                .orElse(null);
        if (entity == null) {
            return null;
        }
        return ProjectConverter.convert(entity);
    }

    public void acceptParticipant(long projectId, long userId){
        Participant participant = participantRepo.findByIds(projectId, userId).orElse(null);
        if (participant == null){
            return;
        }
        participant.setStatus(true);
        participantRepo.save(participant);
    }

    public void rejectParticipant(long projectId, long userId){
        Participant participant = participantRepo.findByIds(projectId, userId).orElse(null);
        if (participant == null){
            return;
        }
        participantRepo.delete(participant);
    }

    private List<UserDTO> getParticipants(Project entity){
        List<UserDTO> participants = new ArrayList<>();
        for (Participant participant : entity.getParticipants()) {
            if (!participant.isStatus()){
                continue;
            }
            userRepo.findById(participant.getUser().getUserId())
                    .ifPresent(user -> participants.add(UserConverter.convert(user)));
        }
        participants.add(UserConverter.convert(entity.getOwner()));
        return participants;
    }

    private List<UserDTO> getRequests(Project entity){
        List<UserDTO> requests = new ArrayList<>();
        for (Participant participant : entity.getParticipants()) {
            if (participant.isStatus()){
                continue;
            }
            userRepo.findById(participant.getUser().getUserId())
                    .ifPresent(user -> requests.add(UserConverter.convert(user)));

        }
        return requests;
    }
}
