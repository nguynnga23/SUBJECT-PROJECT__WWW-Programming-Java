package com.example.backend.services;

import com.example.backend.models.CandidateSkill;

import java.util.List;

public interface CandidateSkillService {
    void save(CandidateSkill candidateSkill);
    void delete(CandidateSkill candidateSkill);
    void update(CandidateSkill candidateSkill);
    List<CandidateSkill> findAll();
}
