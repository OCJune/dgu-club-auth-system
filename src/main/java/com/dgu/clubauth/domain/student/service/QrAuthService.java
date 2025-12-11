package com.dgu.clubauth.domain.student.service;

import com.dgu.clubauth.domain.student.entity.AuthLog;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.enums.Type;
import com.dgu.clubauth.domain.student.repository.AuthLogRepository;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import com.dgu.clubauth.global.exception.InvalidRequestException;
import com.dgu.clubauth.global.exception.ResourceNotFoundException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class QrAuthService {
    
    private final StudentRepository studentRepository;
    private final AuthLogRepository authLogRepository;
    
    @Value("${jwt.secret:dgu-club-auth-system-secret-key-for-qr-authentication-must-be-at-least-256-bits}")
    private String jwtSecret;
    
    @Value("${jwt.expiration:300}") // 기본 5분
    private Long jwtExpirationSeconds;
    
    /**
     * QR 코드 생성 (JWT 토큰 기반)
     */
    @Transactional(readOnly = true)
    public String generateToken(Long studentId) {
        // 학생 존재 여부 확인
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("학생", studentId));
        
        // JWT 토큰 생성
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(jwtExpirationSeconds);
        
        return Jwts.builder()
                .subject(String.valueOf(studentId))
                .claim("name", student.getName())
                .claim("deptName", student.getDeptName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();
    }
    
    /**
     * QR 코드 이미지 생성 (Base64)
     */
    public String generateQrCodeImage(String token) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(token, BarcodeFormat.QR_CODE, 300, 300);
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            
            byte[] qrCodeBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(qrCodeBytes);
        } catch (WriterException | IOException e) {
            log.error("QR 코드 생성 실패", e);
            throw new InvalidRequestException("QR 코드 생성에 실패했습니다.");
        }
    }
    
    /**
     * QR 토큰 검증 및 인증 로그 기록
     */
    @Transactional
    public AuthLog verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            
            Long studentId = Long.parseLong(claims.getSubject());
            
            // 학생 존재 여부 재확인
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("학생", studentId));
            
            // 인증 성공 로그 기록
            AuthLog authLog = AuthLog.builder()
                    .success(true)
                    .type(Type.QR)
                    .student(student)
                    .build();
            
            return authLogRepository.save(authLog);
            
        } catch (JwtException e) {
            log.error("JWT 토큰 검증 실패: {}", e.getMessage());
            throw new InvalidRequestException("유효하지 않거나 만료된 토큰입니다.");
        } catch (ResourceNotFoundException e) {
            log.error("학생 조회 실패: {}", e.getMessage());
            throw e;
        }
    }
}
