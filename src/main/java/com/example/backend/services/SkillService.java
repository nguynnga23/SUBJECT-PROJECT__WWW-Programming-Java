package com.example.backend.services;

import com.example.backend.models.Skill;

import java.util.List;

public interface SkillService {
    void save(Skill skill);
    void delete(Long id);
    void update(Skill skill, Long id);
    List<Skill> findAll();
    Skill findById(Long id);
}
