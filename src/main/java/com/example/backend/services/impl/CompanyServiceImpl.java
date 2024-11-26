package com.example.backend.services.impl;

import com.example.backend.models.Company;
import com.example.backend.repositories.CompanyRepository;
import com.example.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void update(Company company, Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.save(company);
        }
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
