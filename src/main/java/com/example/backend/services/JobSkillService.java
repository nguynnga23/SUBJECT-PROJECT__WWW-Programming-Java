package com.example.backend.services;

import com.example.backend.models.JobSkill;
import com.example.backend.models.JobSkillId;

import java.util.List;

public interface JobSkillService {
    void save (JobSkill jobSkill);
    void update (JobSkill jobSkill, JobSkillId id);
    void delete (JobSkillId id);
    List<JobSkill> findAll ();
}
