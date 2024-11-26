package com.example.backend.enums;

public enum SkillLevel {
    MASTER("Master"), BEGINNER("Beginner"), ADVANCED("Advanced"), PROFESSIONAL("Professional"), IMTERMEDIATE("Imtermediate");
    private String skillLevel;

    private SkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return skillLevel;
    }
}
