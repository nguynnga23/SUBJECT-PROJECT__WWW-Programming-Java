package com.example.frontend.controllers;

import com.example.backend.services.AddressService;
import com.example.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "companies/homepage-company";
    }
    @GetMapping("/profile")
    public String showProfileCompanyPage(Model model) {
        return "companies/profile-company";
    }
    @GetMapping("/dashboard-company")
    public String showDashboardCompanyPage(Model model) {
        return "companies/dashboard-company";
    }
    @GetMapping("/job-management")
    public String showCompanies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "companies/job-management";
    }
}
