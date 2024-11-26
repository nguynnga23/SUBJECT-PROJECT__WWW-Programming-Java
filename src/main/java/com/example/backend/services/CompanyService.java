package com.example.backend.services;

import com.example.backend.models.Company;

import java.util.List;

public interface CompanyService {
    void save(Company company);
    void update(Company company, Long id);
    void delete(Long id);
    List<Company> findAll();
}
