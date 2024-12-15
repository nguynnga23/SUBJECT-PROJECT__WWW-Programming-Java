package com.example.frontend.controllers;

import com.example.backend.models.Candidate;
import com.example.backend.models.Job;
import com.example.backend.repositories.CandidateRepository;
import com.example.backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class GuestController {
    private final CandidateRepository candidateRepository;
    private final CandidateService candidateService;
    private final AddressService addressService;
    private final UserService userService;
    private final SkillService skillService;
    private final CandidateSkillService candidateSkillService;
    private final ExperienceService experienceService;
    private final CompanyService companyService;
    private final JobService jobService;


    @Autowired
    public GuestController(CandidateRepository candidateRepository, CandidateService candidateService, AddressService addressService, UserService userService, SkillService skillService, CandidateSkillService candidateSkillService, ExperienceService experienceService, CompanyService companyService, JobService jobService) {
        this.candidateRepository = candidateRepository;
        this.candidateService = candidateService;
        this.addressService = addressService;
        this.userService = userService;
        this.skillService = skillService;
        this.candidateSkillService = candidateSkillService;
        this.experienceService = experienceService;
        this.companyService = companyService;
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Candidate> candidates = candidateService.findALl();
        List<Job> jobs = jobService.getJobs();
        model.addAttribute("candidates", candidates);
        model.addAttribute("jobs", jobs);
        return "common/homepage";
    }
}
