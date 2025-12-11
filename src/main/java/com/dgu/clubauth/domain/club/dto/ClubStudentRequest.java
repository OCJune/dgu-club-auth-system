package com.dgu.clubauth.domain.club.dto;

import com.dgu.clubauth.domain.club.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ClubStudentRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ClubStudentJoinRequest", description = "동아리 가입 요청")
    public static class Join {
        @Schema(description = "동아리 ID", example = "1")
        private Long clubId; // 동아리 아이디
        @Schema(description = "학생 학번", example = "2021110002")
        private Long studentId; // 학번
        @Schema(description = "직책", example = "부원")
        private Role role; // 직책
    }
}
