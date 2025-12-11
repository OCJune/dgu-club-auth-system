package com.dgu.clubauth.domain.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QrAuthResponse {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Generate {
        private String token; // 생성된 JWT 토큰
        private String qrCodeBase64; // Base64로 인코딩된 QR 코드 이미지
        private Long expiresIn; // 토큰 만료 시간 (초)
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Verify {
        private Boolean success; // 인증 성공 여부
        private String message; // 인증 결과 메시지
        private Long studentId; // 인증된 학번
        private String studentName; // 인증된 학생 이름
        private Long authLogId; // 생성된 인증 로그 ID
    }
}
