package com.example.backend.services;

import com.example.backend.models.Candidate;
import org.springframework.data.domain.*;

import java.util.Optional;

public interface CandidateService {
    Page<Candidate> findAllCandidates(int pageNo, int pageSize, String sortBy, String sortDirection);
    Optional<Candidate> findCandidateById(Long id);
    Optional<Candidate> findCandidateByEmail(String email);
    void save(Candidate candidate);
    void delete(Long id);
    void update(Candidate candidate, Long id);
}
