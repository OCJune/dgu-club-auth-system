package com.dgu.clubauth.domain.student.repository;

import com.dgu.clubauth.domain.student.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
