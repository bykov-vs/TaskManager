package com.coursework.TaskManager.repository;

import com.coursework.TaskManager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM projects p WHERE p.name = ?1 AND p.user_id = ?2")
    Optional<Project> findByName(String name, long userId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM projects p WHERE p.user_id = ?1")
    List<Project> findAllByOwnerId(long id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM projects WHERE project_id IN (SELECT project_id FROM participants p WHERE p.user_id = ?1)")
    List<Project> findAllByParticipantsId(long id);

    @Query(nativeQuery = true,
            value = "INSERT INTO participants VALUES (?1, ?2)")
    List<Project> saveParticipant(long project_id, long user_id);
}
