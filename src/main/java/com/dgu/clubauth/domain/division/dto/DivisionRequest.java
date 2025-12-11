package com.dgu.clubauth.domain.division.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DivisionRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "DivisionCreateRequest", description = "분과 등록 요청")
    public static class Create {
        @Schema(description = "분과명", example = "학술문화분과")
        private String name; // 분과명
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String name; // 분과명
    }
}
