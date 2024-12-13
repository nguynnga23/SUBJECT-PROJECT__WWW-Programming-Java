package com.example.frontend.controllers;

import com.example.backend.models.*;
import com.example.backend.services.*;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AddressService addressService;
    private final CandidateService candidateService;
    private final CompanyService companyService;
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterController(AddressService addressService, CandidateService candidateService, CompanyService companyService, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.addressService = addressService;
        this.candidateService = candidateService;
        this.companyService = companyService;
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String register() {
        return "/auth/register";
    }

    @RequestMapping("/candidate")
    public String registerCandidate(@RequestParam("name") String name, @RequestParam("dob") String dob, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("city") String city, @RequestParam("country") String country, @RequestParam("number") String number, @RequestParam("street") String street, @RequestParam("zipcode") String zipCode, @RequestParam("password") String password, Model model) {
        Candidate candidate = new Candidate();
        candidate.setFullName(name);
        candidate.setDob(LocalDate.parse(dob));
        candidate.setEmail(email);
        candidate.setPhone(phone);

        Address address = new Address();
        address.setCity(city);
        address.setCountry(CountryCode.VN);
        address.setStreet(street);
        address.setZipcode(zipCode);
        try {
            // Save address
            addressService.save(address);
            candidate.setAddress(address);

            // Save candidate
            candidateService.save(candidate);

            // Save user
            User user = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(1l));
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setCandidate(candidate);
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register?fail";
        }
        return "redirect:/register?success";
    }

    @RequestMapping("/company")
    public String registerCompany(@RequestParam("name") String name, @RequestParam("webUrl") String webUrl, @RequestParam("about") String about, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("city") String city, @RequestParam("country") String country, @RequestParam("number") String number, @RequestParam("street") String street, @RequestParam("zipcode") String zipCode, @RequestParam("password") String password, Model model) {
        Company company = new Company();
        company.setWebUrl(webUrl);
        company.setCompName(name);
        company.setEmail(email);
        company.setPhone(phone);
        company.setAbout(about);

        Address address = new Address();
        address.setCity(city);
        address.setCountry(CountryCode.VN);
        address.setStreet(street);
        address.setZipcode(zipCode);
        try {
            // Save address
            addressService.save(address);
            company.setAddress(address);

            // Save company
            companyService.save(company);

            // Save user
            User user = new User();
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findById(2l));
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setCompany(company);
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register?fail";
        }
        return "redirect:/register?success";
    }
}
