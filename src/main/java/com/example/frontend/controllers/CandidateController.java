package com.example.frontend.controllers;

import com.example.backend.enums.SkillLevel;
import com.example.backend.enums.SkillType;
import com.example.backend.models.*;
import com.example.backend.repositories.CandidateRepository;
import com.example.backend.services.*;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
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
    public CandidateController(CandidateRepository candidateRepository, CandidateService candidateService, AddressService addressService, UserService userService, SkillService skillService, CandidateSkillService candidateSkillService, ExperienceService experienceService, CompanyService companyService, JobService jobService) {
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

    // Begin show views
    @GetMapping("/list")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/candidates";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        return "applicants/homepage-candidate";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal) {
        //Lấy danh sách enum SkillType
        List<SkillType> skillTypes = Arrays.asList(SkillType.values());
        model.addAttribute("skillTypes", skillTypes);

        //Lấy danh sách enum SkillLevels
        List<SkillLevel> skillLevels = Arrays.asList(SkillLevel.values());
        model.addAttribute("skillLevels", skillLevels);

        // Lấy danh sách CandidateSkill
        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(principal.getName());
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            model.addAttribute("candidate", candidate);

            // Proceed with your logic for the candidate
            Set<CandidateSkill> candidateSkills = candidate.getCandidateSkills();
            model.addAttribute("candidateSkills", candidateSkills);
            Set<Experience> experiences = candidate.getExperiences();
            model.addAttribute("experiences", experiences);
        } else {
            // Handle case when candidate is not found, e.g., log error or notify user
            System.out.println("Candidate not found for email: " + principal.getName());
        }

        // Lấy danh sách CandidateExperience
        return "applicants/profile-candidate";
    }

    @GetMapping("/job-search")
    public String showFindJobPage(Model model) {
        List<Job> jobs = jobService.getJobs();
        model.addAttribute("jobs", jobs);
        return "applicants/job-search";
    }

    @GetMapping("/company-search")
    public String showFindCompanyPage(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);
        Page<Company> companyPage = companyService.fillALlCompanies(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("showBreadCrumb", false);
        model.addAttribute("companyPage", companyPage);
        int totalPages = companyPage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "applicants/company-search";
    }

    @GetMapping("/company-detail")
    public String showCompanyDetailPage(@RequestParam("id") Long id, Model model) {
        Company company = companyService.findById(id).get();
        model.addAttribute("company", company);
        model.addAttribute("showBreadCrumb", true);
        return "applicants/company-search";
    }

    @GetMapping("/dashboard-candidate")
    public String showDashBoardCandidatePage(Model model) {
        return "applicants/dashboard-candidate";
    }

    @GetMapping("/candidates")
    public String showCandidateListPaging(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateService.findAllCandidates(currentPage - 1, pageSize, "id", "asc");
        model.addAttribute("candidatePage", candidatePage);
        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "candidates/candidates-paging";
    }

    @GetMapping("/add-candidate-form")
    public String showRegisterCandidateForm(Model model) {
        return "candidates/add-candidate";
    }

    @GetMapping("/skills/add-form")
    public String showAddSkillForm(Model model) {
        return "applicants/skill-add-form";
    }
    // End show views

    @RequestMapping(value = "/edit-profile")
    public String editProfileCandidate(@RequestParam("name") String name,
                                       @RequestParam("dob") LocalDate dob,
                                       @RequestParam("email") String email,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("city") String city,
                                       @RequestParam("country") String country,
                                       @RequestParam("number") String number,
                                       @RequestParam("street") String street,
                                       @RequestParam("zipcode") String zipcode,
                                       Principal principal) {
        // Lấy thông tin ứng viên từ cơ sở dữ liệu (hoặc SecurityContext nếu cần)
        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(principal.getName());
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            candidate.setFullName(name);
            candidate.setDob(dob);
            candidate.setEmail(email);
            candidate.setPhone(phone);
            Address address = candidate.getAddress();
            address.setCity(city);
            address.setCountry(CountryCode.valueOf(country));
            address.setNumber(number);
            address.setStreet(street);
            address.setZipcode(zipcode);
            addressService.update(address, address.getId());
            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            candidateService.update(candidate, candidate.getId());
        } else {
            System.out.println("Candidate not found for email: " + principal.getName());
        }
        return "redirect:/candidates/profile";  // Sau khi cập nhật thành công, chuyển hướng đến trang hồ sơ
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Candidate candidateToEdit) {
        return principal != null && principal.getName().equals(candidateToEdit.getEmail());
    }

    @RequestMapping("/add-skill")
    public String addSkillCandidate(Model model, Principal principal,
                                    @RequestParam("skillName") String skillName,
                                    @RequestParam("skillDescription") String skillDescription,
                                    @RequestParam("skillType") String skillType,
                                    @RequestParam("skillLevel") String skillLevel,
                                    @RequestParam("moreInfo") String moreInfo) {

        Skill skill = new Skill();
        skill.setSkillName(skillName);
        skill.setSkillDescription(skillDescription);
        skill.setType(SkillType.fromString(skillType));
        skillService.save(skill);

        System.out.println(skill.toString());
        System.out.println(principal.getName());

        // Ensure the candidate is found by email (handling multiple results case)
        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(principal.getName());

        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            // Proceed with your logic for the candidate
            System.out.println(candidate.toString());

            CandidateSkill candidateSkill = new CandidateSkill();
            candidateSkill.setCan(candidate);
            candidateSkill.setSkill(skill);
            candidateSkill.setSkillLevel(SkillLevel.fromString(skillLevel));
            candidateSkill.setMoreInfos(moreInfo);
            CandidateSkillId candidateSkillId = new CandidateSkillId();
            candidateSkillId.setSkillId(skill.getId());
            candidateSkillId.setCanId(candidate.getId());
            candidateSkill.setId(candidateSkillId);
            candidateSkillService.save(candidateSkill);
        } else {
            // Handle case when candidate is not found, e.g., log error or notify user
            System.out.println("Candidate not found for email: " + principal.getName());
        }

        return "redirect:/candidates/profile";
    }


    @RequestMapping("/skills/edit")
    public String editSkillCandidate(Model model, Principal principal, @RequestParam("skillName") String skillname, @RequestParam("skillDescription") String skilldescription, @RequestParam("skillType") String skillType, @RequestParam("skillLevel") String skillLevel, @RequestParam("moreInfo") String moreInfo) {
        return "redirect:/candidates/profile";
    }

    @RequestMapping("/experiences/add")
    public String addExperienceCandidate(Model model, Principal principal, @RequestParam("company") String company, @RequestParam("role") String role, @RequestParam("toDate") LocalDate toDate, @RequestParam("fromDate") LocalDate fromDate, @RequestParam("workDescription") String workDescription) {
        Optional<Candidate> candidateOpt = candidateRepository.findByEmail(principal.getName());
        if (candidateOpt.isPresent()) {
            Candidate candidate = candidateOpt.get();
            Experience experience = new Experience();
            experience.setCan(candidate);
            experience.setCompanyName(company);
            experience.setRole(role);
            experience.setFromDate(fromDate);
            experience.setToDate(toDate);
            experience.setWorkDescription(workDescription);
            experienceService.save(experience);
        } else {
            System.out.println("Candidate not found for email: " + principal.getName());
        }
        return "redirect:/candidates/profile";
    }

    @RequestMapping("/experiences/edit")
    public String editExperienceCandidate(Model model, Principal principal, @RequestParam("company") String company, @RequestParam("role") String role, @RequestParam("toDate") LocalDate toDate, @RequestParam("fromDate") LocalDate fromDate, @RequestParam("workDescription") String workDescription) {
        return "redirect:/candidates/profile";
    }
}
