package com.example.backend.services;

import com.example.backend.models.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    void update(Role role, Long id);
    void delete(Long id);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
}
