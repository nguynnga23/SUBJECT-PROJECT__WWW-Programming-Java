package com.example.backend.services;

import com.example.backend.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
//    void save(User user);
    User save(User user);
}
