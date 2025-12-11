package com.dgu.clubauth.domain.club.dto;

import com.dgu.clubauth.domain.club.entity.mapping.ClubStudent;
import com.dgu.clubauth.domain.club.enums.Role;
import com.dgu.clubauth.global.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubStudentResponse {
    private Long id; // 아이디
    private Status status; // 상태
    private LocalDateTime inactivatedAt; // 비활성화일
    private Role role; // 직책
    private Long clubId; // 동아리 아이디
    private String clubName; // 동아리명
    private Long studentId; // 학번
    private String studentName; // 학생 이름
    private LocalDateTime createdAt; // 가입일
    private LocalDateTime updatedAt;
    
    public static ClubStudentResponse from(ClubStudent clubStudent) {
        return ClubStudentResponse.builder()
                .id(clubStudent.getId())
                .status(clubStudent.getStatus())
                .inactivatedAt(clubStudent.getInactivatedAt())
                .role(clubStudent.getRole())
                .clubId(clubStudent.getClub().getId())
                .clubName(clubStudent.getClub().getName())
                .studentId(clubStudent.getStudent().getId())
                .studentName(clubStudent.getStudent().getName())
                .createdAt(clubStudent.getCreatedAt())
                .updatedAt(clubStudent.getUpdatedAt())
                .build();
    }
}
