package com.dgu.clubauth.domain.student.dto;

import com.dgu.clubauth.domain.student.entity.AuthLog;
import com.dgu.clubauth.domain.student.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLogResponse {
    private Long id; // 아이디
    private Boolean success; // 성공 여부
    private Type type; // 인증 방식
    private Long studentId; // 학번
    private String studentName; // 학생 이름
    private LocalDateTime createdAt; // 인증 시도 시각
    
    public static AuthLogResponse from(AuthLog authLog) {
        return AuthLogResponse.builder()
                .id(authLog.getId())
                .success(authLog.getSuccess())
                .type(authLog.getType())
                .studentId(authLog.getStudent().getId())
                .studentName(authLog.getStudent().getName())
                .createdAt(authLog.getCreatedAt())
                .build();
    }
}
