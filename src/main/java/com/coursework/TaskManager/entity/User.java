package com.coursework.TaskManager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Project> projects;

}
