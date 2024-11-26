package com.example.frontend.controllers;

import com.example.backend.models.Address;
import com.example.backend.models.Candidate;
import com.example.backend.models.User;
import com.example.backend.services.AddressService;
import com.example.backend.services.CandidateService;
import com.example.backend.services.UserService;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class UserController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

//    @GetMapping("/register")
//    public String register() {
//        return "/auth/register";
//    }
//
//    @RequestMapping("/register")
//    public String registerCandidate(@RequestParam("name") String name, @RequestParam("dob") String dob, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("city") String city, @RequestParam("country") String country, @RequestParam("number") String number, @RequestParam("street") String street, @RequestParam("zipcode") String zipCode, @RequestParam("password") String password, Model model) {
//        Candidate candidate = new Candidate();
//        candidate.setFullName(name);
//        candidate.setDob(LocalDate.parse(dob));
//        candidate.setEmail(email);
//        candidate.setPhone(phone);
//
//        Address address = new Address();
//        address.setCity(city);
//        address.setCountry(CountryCode.VN);
//        address.setStreet(street);
//        address.setZipcode(zipCode);
//        // Save address
//        addressService.save(address);
//        candidate.setAddress(address);
//        // Save candidate
//        candidateService.save(candidate);
//
//        User user = new User();
//        user.setPassword(bCryptPasswordEncoder.encode(password));  // Bạn có thể thay đổi mật khẩu mặc định hoặc thực hiện một quy trình khác để tạo mật khẩu
//        user.setCandidate(candidate);  // Gắn Candidate vào User
//
//        //Save user
//        userService.save(user);
//        return "";
//    }

}
