package com.example.backend.services;

import com.example.backend.models.Experience;

import java.util.List;

public interface ExperienceService {
    void save(Experience experience);
    void update(Experience experience, Long id);
    void delete(Long id);
    List<Experience> findAll();
}
