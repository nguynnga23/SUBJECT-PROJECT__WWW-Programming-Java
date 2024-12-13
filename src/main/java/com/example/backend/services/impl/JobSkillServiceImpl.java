package com.example.backend.services.impl;

import com.example.backend.models.JobSkill;
import com.example.backend.models.JobSkillId;
import com.example.backend.repositories.JobSkillRepository;
import com.example.backend.services.JobSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSkillServiceImpl implements JobSkillService {
    private final JobSkillRepository jobSkillRepository;
    @Autowired
    public JobSkillServiceImpl(JobSkillRepository jobSkillRepository) {
        this.jobSkillRepository = jobSkillRepository;
    }

    @Override
    public void save(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public void update(JobSkill jobSkill, JobSkillId id) {
        if (jobSkillRepository.findById(id).isPresent()) {
            jobSkillRepository.save(jobSkill);
        }
    }

    @Override
    public void delete(JobSkillId id) {
        jobSkillRepository.deleteById(id);
    }

    @Override
    public List<JobSkill> findAll() {
        return jobSkillRepository.findAll();
    }
}
