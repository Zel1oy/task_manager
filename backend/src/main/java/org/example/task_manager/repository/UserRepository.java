package org.example.task_manager.repository;

import org.example.task_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);
}
