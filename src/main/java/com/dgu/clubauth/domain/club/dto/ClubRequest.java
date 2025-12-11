package com.dgu.clubauth.domain.club.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ClubRequest {
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "ClubCreateRequest", description = "동아리 등록 요청")
    public static class Create {
        @Schema(description = "동아리명", example = "컴퓨터과학 동아리")
        private String name; // 동아리명
        @Schema(description = "중앙동아리 등록일", example = "2024-03-01T00:00:00")
        private LocalDateTime designatedAt; // 중앙동아리 등록일
        @Schema(description = "지도교수 이름 (선택)", example = "김교수")
        private String professor; // 지도교수 이름
        @Schema(description = "대표자 학번", example = "2021110001")
        private Long presidentId; // 대표자 학번
        @Schema(description = "분과 ID", example = "1")
        private Long divisionId; // 분과 아이디
    }
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String name; // 동아리명
        private String professor; // 지도교수 이름
        private Long presidentId; // 대표자 학번
        private Long divisionId; // 분과 아이디
    }
}
