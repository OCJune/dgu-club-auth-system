package com.dgu.clubauth.domain.club.service;

import com.dgu.clubauth.domain.club.entity.Club;
import com.dgu.clubauth.domain.club.entity.mapping.ClubStudent;
import com.dgu.clubauth.domain.club.repository.ClubRepository;
import com.dgu.clubauth.domain.club.repository.ClubStudentRepository;
import com.dgu.clubauth.domain.club.enums.Role; // 동아리 직책 ENUM (PRESIDENT, MEMBER 등)
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import com.dgu.clubauth.global.enums.Status; // 상태 ENUM (ACTIVE, INACTIVE)
import com.dgu.clubauth.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClubStudentService {

    private final ClubStudentRepository clubStudentRepository;
    private final ClubRepository clubRepository;
    private final StudentRepository studentRepository;

    /**
     * 학생을 특정 동아리의 부원으로 등록하거나 직책을 부여하는 새로운 이력 레코드를 생성합니다.
     */
    @Transactional
    public ClubStudent joinClubAndSetRole(Long studentId, Long clubId, Role role) {

        // 1. 참조 엔티티 조회 및 검증
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("학생", studentId));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("동아리", clubId));

        // 2. 새로운 ClubStudent 이력 엔티티 생성
        ClubStudent newMembership = ClubStudent.builder()
                .student(student)
                .club(club)
                .role(role) // 부여된 직책
                .status(Status.ACTIVE) // 기본 ACTIVE 상태
                .build();

        // 3. DB에 저장 (INSERT)
        return clubStudentRepository.save(newMembership);
    }

    /**
     * 학생의 동아리 소속을 비활성화(탈퇴 처리)하고 비활성화 시각을 기록합니다.
     */
    @Transactional
    public void inactivateMembership(Long clubStudentId) {
        ClubStudent membership = clubStudentRepository.findById(clubStudentId)
                .orElseThrow(() -> new ResourceNotFoundException("동아리 소속 이력", clubStudentId));

        // 1. 상태 변경 (setter나 변경 메소드가 엔티티에 정의되어 있다고 가정)
        membership.setStatus(Status.INACTIVE);
        membership.setInactivatedAt(LocalDateTime.now());

        // @Transactional에 의해 자동 UPDATE (Dirty Checking)
    }
}