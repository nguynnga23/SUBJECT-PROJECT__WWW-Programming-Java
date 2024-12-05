package com.example.backend.services;

import com.example.backend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);
    void update(User user, Long id);
    void delete(Long id);
    List<User> findAll();
}
