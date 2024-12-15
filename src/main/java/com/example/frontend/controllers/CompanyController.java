package com.example.frontend.controllers;

import com.example.backend.enums.SkillLevel;
import com.example.backend.enums.SkillType;
import com.example.backend.models.*;
import com.example.backend.repositories.JobRepository;
import com.example.backend.repositories.JobSkillRepository;
import com.example.backend.services.*;
import com.neovisionaries.i18n.CountryCode;
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
    private final JobSkillService jobSkillService;

    @Autowired
    public CompanyController(CompanyService companyService, AddressService addressService,
                             JobRepository jobRepository, JobService jobService, SkillService skillService,
                             JobSkillService jobSkillService) {
        this.companyService = companyService;
        this.addressService = addressService;
        this.jobService = jobService;
        this.skillService = skillService;
        this.jobSkillService = jobSkillService;
    }


    // Begin show views
    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "companies/homepage-company";
    }

    @GetMapping("/profile")
    public String showProfileCompanyPage(Model model, Principal principal) {
        Optional<Company> companyOpt = companyService.findByEmail(principal.getName());
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            model.addAttribute("company", company);
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
            model.addAttribute("showBreadCrumb", false);
        } else {
            System.out.println("Company not found: " + principal.getName());
        }
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
            model.addAttribute("showBreadCrumb", false);

        } else {
            System.out.println("Company not found: " + principal.getName());
        }
        return "companies/job-management";
    }

    @GetMapping("/jobs/add")
    public String showAddJobPage(Model model, Principal principal) {
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
            model.addAttribute("showBreadCrumb", true);
        } else {
            System.out.println("Company not found: " + principal.getName());
        }
        return "companies/job-management";
    }

    // End show views

    // Jobs
    @RequestMapping("/edit-profile")
    public String editProfile(Model model, Principal principal,
                              @RequestParam("name") String name, @RequestParam("webUrl") String webUrl, @RequestParam("about") String about, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("city") String city, @RequestParam("country") String country, @RequestParam("number") String number, @RequestParam("street") String street, @RequestParam("zipcode") String zipCode) {
        Optional<Company> companyOpt = companyService.findByEmail(principal.getName());
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            company.setCompName(name);
            company.setAbout(about);
            company.setWebUrl(webUrl);
            company.setEmail(email);
            company.setPhone(phone);
            Address address = company.getAddress();
            address.setCity(city);
            address.setCountry(CountryCode.valueOf(country));
            address.setNumber(number);
            address.setStreet(street);
            address.setZipcode(zipCode);
            addressService.update(address, address.getId());
            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            companyService.update(company, company.getId());
        }
        return "redirect:/companies/profile";
    }

    @RequestMapping("/jobs/add")
    public String addJob(Model model, Principal principal,
                         @RequestParam("jobName") String jobName,
                         @RequestParam("jobDesc") String jobDesc,
                         @RequestParam("skillName") String skillName,
                         @RequestParam("skillDescription") String skillDescription,
                         @RequestParam("skillType") String skillType,
                         @RequestParam("skillLevel") String skillLevel,
                         @RequestParam("moreInfo") String moreInfo) {
        Optional<Company> companyOpt = companyService.findByEmail(principal.getName());
        if (companyOpt.isPresent()) {
            Company company = companyOpt.get();
            Job job = new Job();
            job.setJobDesc(jobDesc);
            job.setJobName(jobName);
            job.setCompany(company);
            jobService.saveJob(job);

            Skill skill = new Skill();
            skill.setSkillName(skillName);
            skill.setSkillDescription(skillDescription);
            skill.setType(SkillType.fromString(skillType));
            skillService.save(skill);

            JobSkill jobSkill = new JobSkill();
            jobSkill.setId(new JobSkillId(job.getId(), skill.getId()));
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkill.setMoreInfos(moreInfo);
            jobSkill.setSkillLevel(SkillLevel.fromString(skillLevel));
            jobSkillService.save(jobSkill);
        } else {
            System.out.println("Company not found: " + principal.getName());
        }
        return "companies/job-management";
    }
}

