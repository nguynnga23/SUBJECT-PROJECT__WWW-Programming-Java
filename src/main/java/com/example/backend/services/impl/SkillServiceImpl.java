package com.example.backend.services.impl;

import com.example.backend.models.Skill;
import com.example.backend.repositories.SkillRepository;
import com.example.backend.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public void update(Skill skill, Long id) {
        if(skillRepository.findById(id).isPresent()) {
            skillRepository.save(skill);
        }
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).get();
    }
}
