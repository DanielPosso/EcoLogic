package com.energymonitor.service;

import com.energymonitor.entities.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    User save(User user);
    // More methods to be implemented
}
