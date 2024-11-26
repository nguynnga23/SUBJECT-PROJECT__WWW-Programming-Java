package com.example.backend.enums;

public enum SkillType {
    SOFT_SKILL("Soft skill"), UNSPECIFIC("Unspecific"), TECHNICAL_SKILL("Technical skill");    private String skillType;
    private SkillType(String skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return skillType;
    }
}
