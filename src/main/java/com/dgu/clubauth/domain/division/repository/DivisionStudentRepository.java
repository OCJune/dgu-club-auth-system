package com.dgu.clubauth.domain.division.repository;

import com.dgu.clubauth.domain.division.entity.mapping.DivisionStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionStudentRepository extends JpaRepository<DivisionStudent, Long> {
}
