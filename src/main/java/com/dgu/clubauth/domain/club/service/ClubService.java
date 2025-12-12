// src/main/java/com/dgu/clubauth/domain/club/service/ClubService.java

package com.dgu.clubauth.domain.club.service;

import com.dgu.clubauth.domain.club.entity.Club;
import com.dgu.clubauth.domain.club.repository.ClubRepository;
import com.dgu.clubauth.domain.division.entity.Division;
import com.dgu.clubauth.domain.division.repository.DivisionRepository;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import com.dgu.clubauth.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성 및 의존성 주입
public class ClubService {

    private final ClubRepository clubRepository;
    private final DivisionRepository divisionRepository;
    private final StudentRepository studentRepository;

    // ----------------------------------------------------------------------
    // 1. CREATE (동아리 등록)
    // ----------------------------------------------------------------------

    /**
          * 새로운 중앙동아리를 등록하고, 소속 분과 및 현재 회장을 지정합니다.
          *
          * @param name               동아리 이름
          * @param divisionId         소속 분과 ID
          * @param presidentStudentId 현재 회장 학생의 학번
          * @param designatedAt       중앙동아리 지정일
          * @param professor          지도교수 이름 (선택)
          * @return 저장된 Club 엔티티
          */
    @Transactional
    public Club createClub(String name, Long divisionId, Long presidentStudentId, LocalDateTime designatedAt, String professor) {

        // 1. 분과(Division) 엔티티 조회 및 검증
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new ResourceNotFoundException("분과", divisionId));

        // 2. 회장(Student) 엔티티 조회 및 검증
        Student president = studentRepository.findById(presidentStudentId)
                .orElseThrow(() -> new ResourceNotFoundException("학생", presidentStudentId));

        // 3. Club 엔티티 생성
        Club newClub = Club.builder()
                .name(name)
                .division(division) // 분과 객체 연결 (FK 설정)
                .president(president) // 회장 학생 객체 연결 (FK 설정)
                .designatedAt(designatedAt)
                .professor(professor) // nullable
                .build();

        // 4. DB에 저장 (INSERT)
        return clubRepository.save(newClub);
    }

    // ----------------------------------------------------------------------
    // 2. UPDATE (동아리 정보 수정)
    // ----------------------------------------------------------------------

    /**
     * 동아리의 소속 분과를 변경합니다.
     *
     * @param clubId         변경할 동아리 ID
     * @param newDivisionId  새로 지정할 분과 ID
     * @return 변경된 Club 엔티티
     */
    @Transactional
    public Club updateClubDivision(Long clubId, Long newDivisionId) {
        // 1. 기존 Club 엔티티 조회
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new ResourceNotFoundException("동아리", clubId));

        // 2. 새로운 Division 엔티티 조회
        Division newDivision = divisionRepository.findById(newDivisionId)
                .orElseThrow(() -> new ResourceNotFoundException("분과", newDivisionId));

        // 3. 엔티티의 필드 업데이트
        club.setDivision(newDivision);

        // 4. @Transactional 덕분에 메소드 종료 시 변경된 상태가 자동으로 DB에 반영(UPDATE)됩니다.
        return club;
    }
}