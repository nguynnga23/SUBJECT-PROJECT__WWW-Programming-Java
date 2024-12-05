package com.example.backend.enums;

public enum SkillLevel {
    MASTER("Master"), BEGINNER("Beginner"), ADVANCED("Advanced"), PROFESSIONAL("Professional"), IMTERMEDIATE("Imtermediate");
    private final String skillLevel;

    SkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return skillLevel;
    }
    // Phương thức tĩnh để chuyển đổi chuỗi thành enum
    public static SkillLevel fromString(String skillLevel) {
        for (SkillLevel type : SkillLevel.values()) {
            if (type.skillLevel.equalsIgnoreCase(skillLevel.trim())) { // Loại bỏ khoảng trắng và so sánh không phân biệt hoa thường
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum level " + skillLevel);
    }
}
