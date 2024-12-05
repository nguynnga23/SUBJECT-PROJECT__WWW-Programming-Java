package com.example.backend.services.impl;

import com.example.backend.models.Experience;
import com.example.backend.repositories.ExperienceRepository;
import com.example.backend.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    @Autowired
    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public void save(Experience experience) {
        experienceRepository.save(experience);
    }

    @Override
    public void update(Experience experience, Long id) {
        if (experienceRepository.findById(id).isPresent()) {
            experienceRepository.save(experience);
        }
    }

    @Override
    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }
}
