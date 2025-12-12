package com.dgu.clubauth.domain.student.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE,
    ;

    @JsonCreator
    public static Gender from(String value) {
        if (value == null) return null;
        String v = value.trim().toUpperCase();
        switch (v) {
            case "M":
            case "MALE":
                return MALE;
            case "F":
            case "FEMALE":
                return FEMALE;
            default:
                throw new IllegalArgumentException("잘못된 성별 값입니다: " + value + " (허용값: M, F, MALE, FEMALE)");
        }
    }
}
