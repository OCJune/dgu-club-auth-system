package com.dgu.clubauth.domain.club.dto;

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
    public static class Create {
        private String name; // 동아리명
        private LocalDateTime designatedAt; // 중앙동아리 등록일
        private String professor; // 지도교수 이름
        private Long presidentId; // 대표자 학번
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
