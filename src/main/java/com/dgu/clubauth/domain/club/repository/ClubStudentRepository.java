package com.dgu.clubauth.domain.club.repository;

import com.dgu.clubauth.domain.club.entity.mapping.ClubStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubStudentRepository extends JpaRepository<ClubStudent, Long> {
}
