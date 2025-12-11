package com.dgu.clubauth.global.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s(ID: %d)을 찾을 수 없습니다.", resourceName, id));
    }
}
