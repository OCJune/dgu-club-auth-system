package com.dgu.clubauth.domain.student.controller;

import com.dgu.clubauth.domain.student.dto.QrAuthRequest;
import com.dgu.clubauth.domain.student.dto.QrAuthResponse;
import com.dgu.clubauth.domain.student.entity.AuthLog;
import com.dgu.clubauth.domain.student.service.QrAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/qr")
@RequiredArgsConstructor
@Tag(name = "QR Authentication", description = "QR 코드 인증 API")
public class QrAuthController {
    
    private final QrAuthService qrAuthService;
    
    @Value("${jwt.expiration:300}")
    private Long jwtExpirationSeconds;
    
    /**
     * QR 코드 생성
     */
    @PostMapping("/generate")
    @Operation(summary = "QR 코드 생성", description = "학생의 인증용 QR 코드를 생성합니다 (JWT 기반).")
    public ResponseEntity<QrAuthResponse.Generate> generateQrCode(
            @RequestBody QrAuthRequest.Generate request) {
        
        // JWT 토큰 생성
        String token = qrAuthService.generateToken(request.getStudentId());
        
        // QR 코드 이미지 생성 (Base64)
        String qrCodeBase64 = qrAuthService.generateQrCodeImage(token);
        
        QrAuthResponse.Generate response = QrAuthResponse.Generate.builder()
                .token(token)
                .qrCodeBase64(qrCodeBase64)
                .expiresIn(jwtExpirationSeconds)
                .build();
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * QR 코드 검증
     */
    @PostMapping("/verify")
    @Operation(summary = "QR 코드 검증", description = "QR 코드의 토큰을 검증하고 인증 로그를 기록합니다.")
    public ResponseEntity<QrAuthResponse.Verify> verifyQrCode(
            @RequestBody QrAuthRequest.Verify request) {
        
        AuthLog authLog = qrAuthService.verifyToken(request.getToken());
        
        QrAuthResponse.Verify response = QrAuthResponse.Verify.builder()
                .success(authLog.getSuccess())
                .message(authLog.getSuccess() ? "인증에 성공했습니다." : "인증에 실패했습니다.")
                .studentId(authLog.getStudent().getId())
                .studentName(authLog.getStudent().getName())
                .authLogId(authLog.getId())
                .build();
        
        return ResponseEntity.ok(response);
    }
}
