package com.coursework.TaskManager.repository;

import com.coursework.TaskManager.entity.Participant;
import com.coursework.TaskManager.entity.User;
import com.coursework.TaskManager.entity.additionally.ParticipantKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, ParticipantKey> {
    @Query(value = "SELECT * FROM participants p WHERE p.project_id = ?1 AND p.user_id = ?2",
            nativeQuery = true)
    Optional<Participant> findByIds(Long projectId, Long userId);
}
