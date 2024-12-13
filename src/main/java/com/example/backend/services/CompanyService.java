package com.example.backend.services;

import com.example.backend.models.Company;
import org.springframework.data.domain.Page;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    void save(Company company);
    void update(Company company, Long id);
    void delete(Long id);
    List<Company> findAll();
    Optional<Company> findByEmail(String email);
    Optional<Company> findById(Long id);
    Page<Company> fillALlCompanies(int pageNo, int pageSize, String sortBy, String sortDirection);
}
