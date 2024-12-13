package com.example.frontend.controllers;

import com.example.backend.enums.SkillLevel;
import com.example.backend.enums.SkillType;
import com.example.backend.models.Company;
import com.example.backend.models.Job;
import com.example.backend.models.JobSkill;
import com.example.backend.models.Skill;
import com.example.backend.repositories.JobRepository;
import com.example.backend.services.AddressService;
import com.example.backend.services.CompanyService;
import com.example.backend.services.JobService;
import com.example.backend.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final AddressService addressService;
    private final JobService jobService;
    private final SkillService skillService;

    @Autowired
    public CompanyController(CompanyService companyService, AddressService addressService,
                             JobRepository jobRepository, JobService jobService, SkillService skillService) {
        this.companyService = companyService;
        this.addressService = addressService;
        this.jobService = jobService;
        this.skillService = skillService;
    }


    // Begin show views
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
    public String showJobManagementPage(Model model, Principal principal) {
        Optional<Company> companyOpt = companyService.findByEmail(principal.getName());
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            Set<Job> jobs = company.getJobs();
            model.addAttribute("jobs", jobs);
            List<Skill> skills = skillService.findAll();
            model.addAttribute("skills", skills);
            //Lấy danh sách enum SkillType
            List<SkillType> skillTypes = Arrays.asList(SkillType.values());
            model.addAttribute("skillTypes", skillTypes);
            //Lấy danh sách enum SkillLevels
            List<SkillLevel> skillLevels = Arrays.asList(SkillLevel.values());
            model.addAttribute("skillLevels", skillLevels);

        } else {
            System.out.println("Company not found: " + principal.getName());
        }
        return "companies/job-management";
    }
    // End show views

    // Jobs
    @RequestMapping("/jobs/add")
    public String addJob(Model model, Principal principal, @RequestParam("jobName") String jobName, @RequestParam("jobDesc") String jobDesc, @RequestParam("jobSkills")Set<JobSkill> jobSkills){
        Optional<Company> companyOpt = companyService.findByEmail(principal.getName());
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            Job job = new Job();
            job.setJobDesc(jobDesc);
            job.setJobName(jobName);
            job.setCompany(company);
            jobService.saveJob(job);
        }else{
            System.out.println("Company not found: " + principal.getName());
        }
        return "companies/job-management";
    };

    @RequestMapping("/jobs/skills/add")
    public String addJobSkill(Model model, Principal principal){
        return "companies/job-management";
    }
}

