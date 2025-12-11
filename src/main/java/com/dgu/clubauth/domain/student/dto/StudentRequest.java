package com.dgu.clubauth.domain.student.dto;

import com.dgu.clubauth.domain.student.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StudentRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long id; // 학번
        private String name; // 학생 이름
        private String deptName; // 소속 학과(부)
        private String phoneNum; // 연락처
        private Gender gender; // 성별
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String name; // 학생 이름
        private String deptName; // 소속 학과(부)
        private String phoneNum; // 연락처
        private Gender gender; // 성별
    }
}
