package com.coursework.TaskManager.entity;

import com.coursework.TaskManager.entity.additionally.ParticipantKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Participants")
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    @EmbeddedId
    private ParticipantKey id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    private boolean status;
}
