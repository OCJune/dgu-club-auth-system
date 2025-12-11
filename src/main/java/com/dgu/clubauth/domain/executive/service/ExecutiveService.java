package com.dgu.clubauth.domain.executive.service;

import com.dgu.clubauth.domain.executive.entity.Executive;
import com.dgu.clubauth.domain.executive.repository.ExecutiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExecutiveService {

    private final ExecutiveRepository executiveRepository;

    @Transactional
    public Executive createExecutive(String department, String role) {
        Executive newExecutive = Executive.builder()
                .department(department)
                .role(role)
                .build();

        return executiveRepository.save(newExecutive);
    }
}
