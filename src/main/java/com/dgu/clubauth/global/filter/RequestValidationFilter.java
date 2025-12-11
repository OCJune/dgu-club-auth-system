package com.dgu.clubauth.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * HTTP 요청 유효성 검사 필터
 * - 손상된 요청 감지 및 로깅
 * - 잘못된 요청에 대한 빠른 응답
 */
@Slf4j
@Component
public class RequestValidationFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RequestValidationFilter initialized");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String method = httpRequest.getMethod();
            String requestURI = httpRequest.getRequestURI();
            
            try {
                // HTTP 메서드 유효성 검사
                if (!isValidHttpMethod(method)) {
                    log.warn("Invalid HTTP method received: {} for URI: {}", method, requestURI);
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                            "Invalid HTTP method");
                    return;
                }
                
                // 요청 진행
                chain.doFilter(request, response);
                
            } catch (Exception e) {
                log.error("Error in RequestValidationFilter", e);
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                if (!httpResponse.isCommitted()) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, 
                            "Invalid request format");
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }
    
    /**
     * HTTP 메서드 유효성 검사
     */
    private boolean isValidHttpMethod(String method) {
        if (method == null || method.isEmpty()) {
            return false;
        }
        
        // 표준 HTTP 메서드 목록
        return method.matches("^(GET|HEAD|POST|PUT|DELETE|CONNECT|OPTIONS|TRACE|PATCH)$");
    }
    
    @Override
    public void destroy() {
        log.info("RequestValidationFilter destroyed");
    }
}
