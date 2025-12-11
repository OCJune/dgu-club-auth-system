package com.dgu.clubauth.global.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 헬스 체크 컨트롤러
 * - 서버 상태 확인
 * - 로드밸런서 헬스 체크용
 */
@RestController
public class HealthController {
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "DGU Club Auth System is running");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> response = new HashMap<>();
        response.put("name", "DGU Central Club Authentication System");
        response.put("version", "1.0.0");
        response.put("status", "ready");
        return ResponseEntity.ok(response);
    }
}
