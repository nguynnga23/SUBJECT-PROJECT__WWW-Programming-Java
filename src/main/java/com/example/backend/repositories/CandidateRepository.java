package com.example.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.models.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
}

