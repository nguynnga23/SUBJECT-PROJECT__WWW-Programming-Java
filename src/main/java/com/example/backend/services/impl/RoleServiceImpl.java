package com.example.backend.services.impl;

import com.example.backend.models.Role;
import com.example.backend.repositories.RoleRepository;
import com.example.backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void update(Role role, Long id) {
        if(roleRepository.findById(id).isPresent()) {
            roleRepository.save(role);
        }
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
