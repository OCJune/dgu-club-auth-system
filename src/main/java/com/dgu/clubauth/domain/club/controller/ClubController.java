package com.dgu.clubauth.domain.club.controller;

import com.dgu.clubauth.domain.club.dto.ClubRequest;
import com.dgu.clubauth.domain.club.dto.ClubResponse;
import com.dgu.clubauth.domain.club.dto.ClubStudentRequest;
import com.dgu.clubauth.domain.club.dto.ClubStudentResponse;
import com.dgu.clubauth.domain.club.entity.Club;
import com.dgu.clubauth.domain.club.entity.mapping.ClubStudent;
import com.dgu.clubauth.domain.club.repository.ClubRepository;
import com.dgu.clubauth.domain.club.service.ClubService;
import com.dgu.clubauth.domain.club.service.ClubStudentService;
import com.dgu.clubauth.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {
    
    private final ClubService clubService;
    private final ClubStudentService clubStudentService;
    private final ClubRepository clubRepository;
    
    /**
     * 동아리 등록
     */
    @PostMapping
    public ResponseEntity<ClubResponse> createClub(@RequestBody ClubRequest.Create request) {
        Club club = clubService.createClub(
                request.getName(),
                request.getDivisionId(),
                request.getPresidentId(),
                request.getDesignatedAt()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ClubResponse.from(club));
    }
    
    /**
     * 동아리 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<ClubResponse>> getAllClubs() {
        List<ClubResponse> clubs = clubRepository.findAll().stream()
                .map(ClubResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(clubs);
    }
    
    /**
     * 동아리 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClubResponse> getClub(@PathVariable Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("동아리", id));
        
        return ResponseEntity.ok(ClubResponse.from(club));
    }
    
    /**
     * 동아리 정보 수정 (분과 변경)
     */
    @PatchMapping("/{id}/division")
    public ResponseEntity<ClubResponse> updateClubDivision(
            @PathVariable Long id,
            @RequestParam Long divisionId) {
        Club club = clubService.updateClubDivision(id, divisionId);
        return ResponseEntity.ok(ClubResponse.from(club));
    }
    
    /**
     * 동아리 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        if (!clubRepository.existsById(id)) {
            throw new ResourceNotFoundException("동아리", id);
        }
        
        clubRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * 동아리 가입
     */
    @PostMapping("/join")
    public ResponseEntity<ClubStudentResponse> joinClub(@RequestBody ClubStudentRequest.Join request) {
        ClubStudent clubStudent = clubStudentService.joinClubAndSetRole(
                request.getStudentId(),
                request.getClubId(),
                request.getRole()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ClubStudentResponse.from(clubStudent));
    }
    
    /**
     * 동아리 탈퇴
     */
    @PatchMapping("/memberships/{membershipId}/inactivate")
    public ResponseEntity<Void> leaveClub(@PathVariable Long membershipId) {
        clubStudentService.inactivateMembership(membershipId);
        return ResponseEntity.ok().build();
    }
}
