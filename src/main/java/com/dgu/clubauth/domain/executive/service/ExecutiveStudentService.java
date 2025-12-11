// src/main/java/com/dgu/clubauth/domain/executive/service/ExecutiveStudentService.java

package com.dgu.clubauth.domain.executive.service;

import com.dgu.clubauth.domain.executive.entity.Executive;
import com.dgu.clubauth.domain.executive.entity.mapping.ExecutiveStudent;
import com.dgu.clubauth.domain.executive.repository.ExecutiveRepository;
import com.dgu.clubauth.domain.executive.repository.ExecutiveStudentRepository;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import com.dgu.clubauth.global.enums.Status; // 상태 ENUM (ACTIVE, INACTIVE)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExecutiveStudentService {

    private final ExecutiveStudentRepository executiveStudentRepository;
    private final ExecutiveRepository executiveRepository;
    private final StudentRepository studentRepository;

    /**
     * 특정 학생에게 연합회 집행부 직책을 임명하는 새로운 이력 레코드를 생성합니다.
     */
    @Transactional
    public ExecutiveStudent appointExecutive(Long studentId, Long executiveId) {

        // 1. 참조 엔티티 조회 및 검증
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("학생(학번: " + studentId + ")을 찾을 수 없습니다."));
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(() -> new NoSuchElementException("집행부 직책(ID: " + executiveId + ")을 찾을 수 없습니다."));

        // 2. 새로운 ExecutiveStudent 이력 엔티티 생성
        ExecutiveStudent newAppointment = ExecutiveStudent.builder()
                .student(student)
                .executive(executive)
                .status(Status.ACTIVE) // 임명 시 ACTIVE 상태
                .build();

        // 3. DB에 저장 (INSERT)
        return executiveStudentRepository.save(newAppointment);
    }

    /**
     * 연합회 직책 임명 이력 레코드를 비활성화(임기 종료) 처리하고 시각을 기록합니다.
     */
    @Transactional
    public void retireExecutive(Long executiveStudentId) {
        ExecutiveStudent appointment = executiveStudentRepository.findById(executiveStudentId)
                .orElseThrow(() -> new NoSuchElementException("집행부 이력(ID: " + executiveStudentId + ")을 찾을 수 없습니다."));

        // 1. 상태 변경
        appointment.setStatus(Status.INACTIVE);
        appointment.setInactivatedAt(LocalDateTime.now());

        // @Transactional에 의해 자동 UPDATE
    }
}