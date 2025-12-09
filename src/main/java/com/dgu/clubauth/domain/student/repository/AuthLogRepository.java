package com.dgu.clubauth.domain.student.repository;

import com.dgu.clubauth.domain.student.entity.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
}
