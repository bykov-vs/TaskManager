package com.coursework.TaskManager.repository;

import com.coursework.TaskManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
