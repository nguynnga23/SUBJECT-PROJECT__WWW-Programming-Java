package com.example.backend.services;

import com.example.backend.models.Job;

import java.util.List;

public interface JobService {
    void saveJob(Job job);
    void deleteJob(Long id);
    void updateJob(Job job, Long id);
    List<Job> getJobs();
    List<Job> findJobsByCompany_Id(String compId);
}
