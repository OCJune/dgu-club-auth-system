package com.dgu.clubauth.domain.division.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DivisionRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
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
