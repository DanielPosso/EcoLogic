// UserRepository interface - Handles database operations for User entity
// Extends JpaRepository to get basic CRUD operations
package com.energymonitor.repository;

import com.energymonitor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by their email
    Optional<User> findByEmail(String email);

    // Check if a user with given email exists
    boolean existsByEmail(String email);
}
