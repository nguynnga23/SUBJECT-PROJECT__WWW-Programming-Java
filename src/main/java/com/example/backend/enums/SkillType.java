package com.example.backend.enums;

public enum SkillType {
    SOFT_SKILL("Soft skill"), UNSPECIFIC("Unspecific"), TECHNICAL_SKILL("Technical skill");
    private final String skillType;
    SkillType(String skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return skillType;
    }
    // Phương thức tĩnh để chuyển đổi chuỗi thành enum
    public static SkillType fromString(String skillType) {
        for (SkillType type : SkillType.values()) {
            if (type.skillType.equalsIgnoreCase(skillType.trim())) { // Loại bỏ khoảng trắng và so sánh không phân biệt hoa thường
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + skillType);
    }
}
