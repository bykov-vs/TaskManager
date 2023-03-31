package com.coursework.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
