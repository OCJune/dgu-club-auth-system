package com.dgu.clubauth.domain.club.dto;

import com.dgu.clubauth.domain.club.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ClubStudentRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Join {
        private Long clubId; // 동아리 아이디
        private Long studentId; // 학번
        private Role role; // 직책
    }
}
