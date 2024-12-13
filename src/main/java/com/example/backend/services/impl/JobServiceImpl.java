package com.example.backend.services.impl;

import com.example.backend.models.Job;
import com.example.backend.repositories.JobRepository;
import com.example.backend.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(Job job, Long id) {
        if(jobRepository.findById(id).isPresent()) {
            jobRepository.save(job);
        }
    }

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findJobsByCompany_Id(String compId) {
        return List.of();
    }
}
