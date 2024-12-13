package com.example;

import com.example.backend.enums.SkillLevel;
import com.example.backend.enums.SkillType;
import com.example.backend.models.*;
import com.example.backend.repositories.*;
import com.example.backend.services.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;


@SpringBootApplication
public class Week08JobApplication {
    @Autowired
    public Week08JobApplication(CandidateService candidateService, AddressService addressService, UserService userService, SkillService skillService, CandidateSkillService candidateSkillService, CompanyService companyService, RoleService roleService, JobService jobService, JobSkillService jobSkillService) {
        this.candidateService = candidateService;
        this.addressService = addressService;
        this.userService = userService;
        this.skillService = skillService;
        this.candidateSkillService = candidateSkillService;
        this.companyService = companyService;
        this.roleService = roleService;
        this.jobService = jobService;
        this.jobSkillService = jobSkillService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Week08JobApplication.class, args);
    }

    private final CandidateService candidateService;
    private final AddressService addressService;
    private final UserService userService;
    private final SkillService skillService;
    private final CandidateSkillService candidateSkillService;
    private final CompanyService companyService;
    private final RoleService roleService;
    private final JobService jobService;
    private final JobSkillService jobSkillService;

    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(CompanyRepository companyRepository, SkillRepository skillRepository) {
        return args -> {
//            Random rand = new Random();
//            for (int i = 1; i <= 1; i++) {
//                // Tạo đối tượng Address với ID tự động tăng
//                Address add = new Address(null, "Quang Trung", "HCM", CountryCode.VN,
//                        rand.nextInt(70000, 80000) + "", rand.nextInt(70000, 80000) + "");
//                addressService.save(add);
//
//                // Tạo đối tượng Candidate với ID tự động tăng và tham chiếu đến Address vừa tạo
//                Candidate candidate = new Candidate(null, LocalDate.of(1998, rand.nextInt(1,13), rand.nextInt(1,29)),
//                        "email_" + i + "@gmail.com", "Name #" + i,
//                        rand.nextLong(1111111111111L, 99999999999999L) + "", add);
//                candidateService.save(candidate);
//                System.out.println("Added candidate " + candidate);
//                User user = new User();
//                user.setCandidate(candidate);
//                passwordEncoder = new BCryptPasswordEncoder();
//                user.setPassword(passwordEncoder.encode("123"));
//                userRepository.save(user);
//                System.out.println("Added user " + user);
//            User user = userRepository.findByCandidateEmailOrCompanyEmail("email_1@gmail.com");
//            if (user != null) {
//                System.out.println("User found: " + user);
//                System.out.println("User email: " + user.getEmail());
//                System.out.println("User password: " + user.getPassword());
//            } else {
//                System.out.println("User not found");
//            }


//            Skill skill = new Skill();
//            skill.setSkillName("Skill 1");
//            skill.setSkillDescription("Skill 1 Description");
//            skill.setType(SkillType.TECHNICAL_SKILL);
//            skillRepository.save(skill);
//            System.out.println(skill.toString());
//            Candidate candidate = candidateRepository.findById(1L).get();
//            System.out.println(candidate.toString());
//            CandidateSkill candidateSkill = new CandidateSkill();
//            candidateSkill.setCan(candidate);
//            candidateSkill.setSkill(skill);
//            candidateSkill.setSkillLevel(SkillLevel.ADVANCED);
//            CandidateSkillId candidateSkillId = new CandidateSkillId();
//            candidateSkillId.setSkillId(skill.getId());
//            candidateSkillId.setCanId(candidate.getId());
//            candidateSkill.setId(candidateSkillId);
//            candidateSkillRepository.save(candidateSkill);
//            System.out.println(candidateSkill.toString());
//            Faker faker = new Faker();
//            for(int i = 0; i < 100; i++){
//                Company company = new Company();
//                company.setEmail(faker.internet().emailAddress());
//                company.setPhone(faker.phoneNumber().phoneNumber());
//                company.setAbout(faker.company().profession());
//                company.setCompName(faker.company().name());
//                Address address = new Address();
//                address.setCity(faker.address().city());
//                address.setStreet(faker.address().streetAddress());
//                address.setCity(faker.address().city());
//                address.setZipcode(faker.address().zipCode());
//                address.setCountry(CountryCode.VN);
//                address.setNumber(faker.address().streetAddressNumber());
//                addressRepository.save(address);
//                company.setAddress(address);
//                company.setWebUrl(faker.company().url());
//                companyRepository.save(company);
//                User user1 = new User();
//                user1.setCompany(company);
//                passwordEncoder = new BCryptPasswordEncoder();
//                user1.setPassword(passwordEncoder.encode("company"));
//                userRepository.save(user1);
//            }
//
//            Set<Role> roles = (Set<Role>) roleRepository.findAll();
//           for (User user : userRepository.findAll()) {
//                if(user.getCompany() != null){
//                    Optional<Role> role = roleRepository.findById(2l);
//                    Set<Role> roles = new HashSet<>();
//                    roles.add(role.get());
//                    user.setRoles(roles);
//                    userRepository.update(user, user.getId());
//                }else{
//                    Optional<Role> role = roleRepository.findById(1l);
//                    Set<Role> roles = new HashSet<>();
//                    roles.add(role.get());
//                    user.setRoles(roles);
//                    userRepository.update(user, user.getId());
//                }
//           }
//
//            Faker faker = new Faker();
//            for (Company company : companyService.findAll()) {
//                Job job = new Job();
//                job.setCompany(company);
//                job.setJobDesc(faker.job().field());
//                job.setJobName(faker.job().position());
//                jobService.saveJob(job);
//            }
        };
    }

};
