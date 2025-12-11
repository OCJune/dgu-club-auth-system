package com.dgu.clubauth.domain.club.dto;

import com.dgu.clubauth.domain.club.entity.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponse {
    private Long id; // 아이디
    private String name; // 동아리명
    private LocalDateTime designatedAt; // 중앙동아리 등록일
    private String professor; // 지도교수 이름
    private Long presidentId; // 대표자 학번
    private String presidentName; // 대표자 이름
    private Long divisionId; // 분과 아이디
    private String divisionName; // 분과명
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static ClubResponse from(Club club) {
        return ClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .designatedAt(club.getDesignatedAt())
                .professor(club.getProfessor())
                .presidentId(club.getPresident().getId())
                .presidentName(club.getPresident().getName())
                .divisionId(club.getDivision().getId())
                .divisionName(club.getDivision().getName())
                .createdAt(club.getCreatedAt())
                .updatedAt(club.getUpdatedAt())
                .build();
    }
}
