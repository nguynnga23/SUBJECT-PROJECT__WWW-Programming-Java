package com.example.backend.services;

import com.example.backend.models.Candidate;
import org.springframework.data.domain.*;

public interface CandidateService {
    Page<Candidate> findAllCandidates(int pageNo, int pageSize, String sortBy, String sortDirection);
    void save(Candidate candidate);
    void delete(Long id);
    void update(Candidate candidate, Long id);
}
