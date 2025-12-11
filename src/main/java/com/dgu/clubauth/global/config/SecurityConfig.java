package com.dgu.clubauth.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Security 설정
 * - 역할 기반 접근 제어 (RBAC)
 * - CORS 설정
 * - CSRF 비활성화 (REST API이므로)
 * - 요청 유효성 검사
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화 (REST API이므로)
                .csrf(AbstractHttpConfigurer::disable)
                
                // CORS 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                
                // 세션 관리 - Stateless (JWT 기반 인증)
                .sessionManagement(session -> 
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                // 요청별 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // QR 인증 엔드포인트는 모두 허용
                        .requestMatchers("/api/auth/qr/**").permitAll()
                        
                        // 헬스 체크 및 기본 정보
                        .requestMatchers("/", "/actuator/**", "/health").permitAll()
                        
                        // 조회(GET) 요청은 인증된 사용자만 허용
                        .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                        
                        // 생성/수정/삭제는 ADMIN 또는 EXECUTIVE 역할만 허용
                        // (향후 UserRole 엔티티 기반으로 동적 권한 관리 가능)
                        .requestMatchers(HttpMethod.POST, "/api/**").permitAll() // 임시로 허용
                        .requestMatchers(HttpMethod.PUT, "/api/**").permitAll() // 임시로 허용
                        .requestMatchers(HttpMethod.PATCH, "/api/**").permitAll() // 임시로 허용
                        .requestMatchers(HttpMethod.DELETE, "/api/**").permitAll() // 임시로 허용
                        
                        // 나머지 요청은 모두 인증 필요
                        .anyRequest().authenticated()
                )
                
                // HTTP Basic 인증 (개발 단계에서 간단한 테스트용)
                .httpBasic(AbstractHttpConfigurer::disable)
                
                // Form 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable);
        
        return http.build();
    }
    
    /**
     * CORS 설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000", 
                "http://localhost:8080",
                "http://localhost:5173" // Vite default
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Content-Type", "Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
