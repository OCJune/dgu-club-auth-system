package com.dgu.clubauth.domain.executive.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ExecutiveRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ExecutiveCreateRequest", description = "집행부 직책 등록 요청")
    public static class Create {
        @Schema(description = "소속 부서", example = "연합회")
        private String department; // 소속
        @Schema(description = "직책명", example = "회장")
        private String role; // 직책
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String department; // 소속
        private String role; // 직책
    }
}
