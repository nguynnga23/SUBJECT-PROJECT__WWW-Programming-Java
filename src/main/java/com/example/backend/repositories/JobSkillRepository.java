package com.example.backend.repositories;

import com.example.backend.models.JobSkill;
import com.example.backend.models.JobSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}
