package com.coursework.TaskManager.entity.additionally;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantKey implements Serializable {
    @Column(name = "project_id")
    Long projectId;

    @Column(name = "user_id")
    Long userId;
}
