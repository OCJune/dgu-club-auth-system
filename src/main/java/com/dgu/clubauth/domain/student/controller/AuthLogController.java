package com.dgu.clubauth.domain.student.controller;

import com.dgu.clubauth.domain.student.dto.AuthLogResponse;
import com.dgu.clubauth.domain.student.entity.AuthLog;
import com.dgu.clubauth.domain.student.repository.AuthLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth-logs")
@RequiredArgsConstructor
public class AuthLogController {
    
    private final AuthLogRepository authLogRepository;
    
    /**
     * 전체 인증 로그 조회
     */
    @GetMapping
    public ResponseEntity<List<AuthLogResponse>> getAllAuthLogs() {
        List<AuthLogResponse> authLogs = authLogRepository.findAll().stream()
                .map(AuthLogResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(authLogs);
    }
    
    /**
     * 특정 학생의 인증 로그 조회
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AuthLogResponse>> getAuthLogsByStudent(@PathVariable Long studentId) {
        List<AuthLog> authLogs = authLogRepository.findAll().stream()
                .filter(log -> log.getStudent().getId().equals(studentId))
                .collect(Collectors.toList());
        
        List<AuthLogResponse> responses = authLogs.stream()
                .map(AuthLogResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
}
