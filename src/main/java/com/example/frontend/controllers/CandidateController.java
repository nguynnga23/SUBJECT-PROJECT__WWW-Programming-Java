package com.example.frontend.controllers;

import com.example.backend.enums.SkillLevel;
import com.example.backend.enums.SkillType;
import com.example.backend.models.Candidate;
import com.example.backend.repositories.CandidateRepository;
import com.example.backend.services.AddressService;
import com.example.backend.services.CandidateService;
import com.example.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private AddressService addressService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

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
    public String showProfilePage(Model model) {
        // Lấy danh sách các giá trị từ enum
        List<SkillType> skillTypes = Arrays.asList(SkillType.values());
        // Thêm danh sách vào model
        model.addAttribute("skillTypes", skillTypes);
        List<SkillLevel> skillLevels = Arrays.asList(SkillLevel.values());
        model.addAttribute("skillLevels", skillLevels);
        return "applicants/profile-candidate";
    }

    @GetMapping("/job-search")
    public String showFindJobPage(Model model) {
        return "applicants/job-search";
    }
    @GetMapping("/company-search")
    public String showFindCompanyPage(Model model) {
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

    ;

    @RequestMapping("/add-skill-candidate")
    public String addSkillCandidate(Model model, Principal principal, @RequestParam("skillName") String skillname, @RequestParam("skillDescription") String skilldescription, @RequestParam("skillType") String skillType, @RequestParam("skillLevel") String skillLevel, @RequestParam("moreInfo") String moreInfo) {

        return "";
    }

    ;

}
