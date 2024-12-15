package com.example.backend.services.impl;

import com.example.backend.models.Candidate;
import com.example.backend.repositories.CandidateRepository;
import com.example.backend.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Page<Candidate> findAllCandidates(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    @Override
    public Optional<Candidate> findCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findCandidateByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public List<Candidate> findALl() {
        return candidateRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public void delete(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public void update(Candidate candidate, Long id) {
        if(candidateRepository.findById(id).isPresent()) {
            candidateRepository.save(candidate);
        }
    }
}
