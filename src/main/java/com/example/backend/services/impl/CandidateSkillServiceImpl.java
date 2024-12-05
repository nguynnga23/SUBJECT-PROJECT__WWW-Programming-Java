package com.example.backend.services.impl;

import com.example.backend.models.CandidateSkill;
import com.example.backend.repositories.CandidateSkillRepository;
import com.example.backend.services.CandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {
    private final CandidateSkillRepository candidateSkillRepository;

    @Autowired
    public CandidateSkillServiceImpl(CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillRepository = candidateSkillRepository;
    }

    @Override
    public void save(CandidateSkill candidateSkill) {
        candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public void delete(CandidateSkill candidateSkill) {
        candidateSkillRepository.delete(candidateSkill);
    }

    @Override
    public void update(CandidateSkill candidateSkill) {
        candidateSkillRepository.save(candidateSkill);
    }

    @Override
    public List<CandidateSkill> findAll() {
        return candidateSkillRepository.findAll();
    }
}
