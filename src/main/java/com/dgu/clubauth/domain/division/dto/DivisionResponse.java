package com.dgu.clubauth.domain.division.dto;

import com.dgu.clubauth.domain.division.entity.Division;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivisionResponse {
    private Long id; // 아이디
    private String name; // 분과명
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static DivisionResponse from(Division division) {
        return DivisionResponse.builder()
                .id(division.getId())
                .name(division.getName())
                .createdAt(division.getCreatedAt())
                .updatedAt(division.getUpdatedAt())
                .build();
    }
}
