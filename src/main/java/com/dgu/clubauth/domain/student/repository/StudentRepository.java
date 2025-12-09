package com.dgu.clubauth.domain.student.repository;

import com.dgu.clubauth.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
