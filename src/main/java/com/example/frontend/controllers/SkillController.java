package com.example.frontend.controllers;

import com.example.backend.enums.SkillType;
import com.example.backend.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/skill-types")
    public List<String> getSkillTypes(){
        return Arrays.stream(SkillType.values()).map(SkillType::toString).toList();
    }
}
