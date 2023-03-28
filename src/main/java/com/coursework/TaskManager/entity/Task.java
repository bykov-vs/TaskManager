package com.coursework.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
