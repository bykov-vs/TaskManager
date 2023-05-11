package com.coursework.TaskManager.converters;

import com.coursework.TaskManager.dto.TaskDTO;
import com.coursework.TaskManager.entity.Task;
import com.coursework.TaskManager.entity.User;

public class TaskConverter {
    public static Task convert(TaskDTO dto){
        Task entity = new Task();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public static TaskDTO convert(Task entity){
        TaskDTO dto = new TaskDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setProjectId(entity.getProject().getProjectId());
        User performer = entity.getPerformer();
        if (performer != null){
            dto.setPerformerId(performer.getUserId());
        }else
            dto.setPerformerId(-1);
        return dto;
    }
}
