package com.dgu.clubauth.domain.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QrAuthRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Generate {
        private Long studentId; // 인증 대상 학번
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Verify {
        private String token; // QR 코드에서 읽은 토큰
    }
}
