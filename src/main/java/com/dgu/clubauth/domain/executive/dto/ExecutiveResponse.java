package com.dgu.clubauth.domain.executive.dto;

import com.dgu.clubauth.domain.executive.entity.Executive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveResponse {
    private Long id; // 아이디
    private String department; // 소속
    private String role; // 직책
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static ExecutiveResponse from(Executive executive) {
        return ExecutiveResponse.builder()
                .id(executive.getId())
                .department(executive.getDepartment())
                .role(executive.getRole())
                .createdAt(executive.getCreatedAt())
                .updatedAt(executive.getUpdatedAt())
                .build();
    }
}
