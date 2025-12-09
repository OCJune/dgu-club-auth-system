package com.dgu.clubauth.domain.student.service;

import com.dgu.clubauth.domain.student.entity.UserRole;
import com.dgu.clubauth.domain.student.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Transactional
    public UserRole createUserRole(String name, String description) {
        UserRole newUserRole = UserRole.builder()
                .name(name)
                .description(description)
                .build();

        return userRoleRepository.save(newUserRole);
    }
}
