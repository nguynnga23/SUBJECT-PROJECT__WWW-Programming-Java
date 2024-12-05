package com.example.backend.repositories;

import com.example.backend.models.CandidateSkill;
import com.example.backend.models.CandidateSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {

}
