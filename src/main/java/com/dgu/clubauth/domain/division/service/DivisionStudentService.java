package com.dgu.clubauth.domain.division.service;

import com.dgu.clubauth.domain.division.entity.Division;
import com.dgu.clubauth.domain.division.entity.mapping.DivisionStudent;
import com.dgu.clubauth.domain.division.repository.DivisionRepository;
import com.dgu.clubauth.domain.division.repository.DivisionStudentRepository;
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
public class DivisionStudentService {

    private final DivisionStudentRepository divisionStudentRepository;
    private final DivisionRepository divisionRepository;
    private final StudentRepository studentRepository;

    /**
     * 특정 학생을 분과장으로 임명하는 새로운 이력 레코드를 생성합니다.
     */
    @Transactional
    public DivisionStudent appointDivisionHead(Long studentId, Long divisionId) {

        // 1. 참조 엔티티 조회 및 검증
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("학생(학번: " + studentId + ")을 찾을 수 없습니다."));
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new NoSuchElementException("분과(ID: " + divisionId + ")를 찾을 수 없습니다."));

        // (선택적) 이전에 ACTIVE 상태였던 해당 분과 분과장이 있다면 INACTIVE로 변경하는 로직이 추가될 수 있음

        // 2. 새로운 DivisionStudent 이력 엔티티 생성
        DivisionStudent newAppointment = DivisionStudent.builder()
                .student(student)
                .division(division)
                .status(Status.ACTIVE) // 임명 시 ACTIVE 상태
                .build();

        // 3. DB에 저장 (INSERT)
        return divisionStudentRepository.save(newAppointment);
    }

    /**
     * 분과장 임명 이력 레코드를 비활성화(임기 종료) 처리하고 시각을 기록합니다.
     */
    @Transactional
    public void retireDivisionHead(Long divisionStudentId) {
        DivisionStudent appointment = divisionStudentRepository.findById(divisionStudentId)
                .orElseThrow(() -> new NoSuchElementException("분과장 이력(ID: " + divisionStudentId + ")을 찾을 수 없습니다."));

        // 1. 상태 변경
        appointment.setStatus(Status.INACTIVE);
        appointment.setInactivatedAt(LocalDateTime.now());

        // @Transactional에 의해 자동 UPDATE
    }
}