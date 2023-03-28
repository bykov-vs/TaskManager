package com.coursework.TaskManager.repository;

import com.coursework.TaskManager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM tasks t WHERE t.name = ?1 AND t.project_id = ?2")
    Optional<Task> findByName(String name, long projectId);

    @Query(nativeQuery = true, value = "SELECT * FROM tasks t WHERE t.project_id = ?1")
    List<Task> findAll(long projectId);
}
