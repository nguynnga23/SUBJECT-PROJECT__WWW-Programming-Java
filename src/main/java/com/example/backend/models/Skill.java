package com.example.backend.models;

import com.example.backend.enums.SkillType;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SkillType type;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills;
    @OneToMany(mappedBy = "skill")
    private Set<JobSkill> jobSkills;
}