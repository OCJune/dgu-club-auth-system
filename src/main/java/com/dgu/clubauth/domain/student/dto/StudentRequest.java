package com.dgu.clubauth.domain.student.dto;

import com.dgu.clubauth.domain.student.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StudentRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "StudentCreateRequest", description = "학생 등록 요청")
    public static class Create {
        @Schema(description = "학번 (직접 지정)", example = "2021110001")
        private Long id; // 학번
        @Schema(description = "학생 이름", example = "홍길동")
        private String name; // 학생 이름
        @Schema(description = "소속 학과(부)", example = "컴퓨터공학과")
        private String deptName; // 소속 학과(부)
        @Schema(description = "연락처", example = "010-1234-5678")
        private String phoneNum; // 연락처
        @Schema(description = "성별", example = "M")
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
