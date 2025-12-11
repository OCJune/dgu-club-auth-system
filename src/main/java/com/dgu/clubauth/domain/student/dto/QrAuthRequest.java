package com.dgu.clubauth.domain.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QrAuthRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "QrAuthGenerateRequest", description = "QR 코드 생성 요청")
    public static class Generate {
        @Schema(description = "인증 대상 학번", example = "2021110002")
        private Long studentId; // 인증 대상 학번
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "QrAuthVerifyRequest", description = "QR 코드 검증 요청")
    public static class Verify {
        @Schema(description = "QR 코드에서 읽은 JWT 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        private String token; // QR 코드에서 읽은 토큰
    }
}
