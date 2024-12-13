package com.example.backend.services;

import com.example.backend.models.Company;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void save(Company company);
    void update(Company company, Long id);
    void delete(Long id);
    List<Company> findAll();
    Optional<Company> findByEmail(String email);
    Page<Company> fillALlCompanies(int pageNo, int pageSize, String sortBy, String sortDirection);
}
