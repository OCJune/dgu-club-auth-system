package com.dgu.clubauth.domain.executive.controller;

import com.dgu.clubauth.domain.executive.dto.ExecutiveRequest;
import com.dgu.clubauth.domain.executive.dto.ExecutiveResponse;
import com.dgu.clubauth.domain.executive.entity.Executive;
import com.dgu.clubauth.domain.executive.repository.ExecutiveRepository;
import com.dgu.clubauth.domain.executive.service.ExecutiveService;
import com.dgu.clubauth.domain.executive.service.ExecutiveStudentService;
import com.dgu.clubauth.global.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/executives")
@RequiredArgsConstructor
@Tag(name = "Executive", description = "집행부 관리 API")
public class ExecutiveController {
    
    private final ExecutiveService executiveService;
    private final ExecutiveStudentService executiveStudentService;
    private final ExecutiveRepository executiveRepository;
    
    /**
     * 집행부 직책 등록
     */
    @PostMapping
    @Operation(summary = "집행부 직책 등록", description = "새로운 집행부 직책을 등록합니다.")
    public ResponseEntity<ExecutiveResponse> createExecutive(@RequestBody ExecutiveRequest.Create request) {
        Executive executive = executiveService.createExecutive(
                request.getDepartment(),
                request.getRole()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(ExecutiveResponse.from(executive));
    }
    
    /**
     * 집행부 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<ExecutiveResponse>> getAllExecutives() {
        List<ExecutiveResponse> executives = executiveRepository.findAll().stream()
                .map(ExecutiveResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(executives);
    }
    
    /**
     * 집행부 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExecutiveResponse> getExecutive(@PathVariable Long id) {
        Executive executive = executiveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("집행부", id));
        
        return ResponseEntity.ok(ExecutiveResponse.from(executive));
    }
    
    /**
     * 집행부 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExecutive(@PathVariable Long id) {
        if (!executiveRepository.existsById(id)) {
            throw new ResourceNotFoundException("집행부", id);
        }
        
        executiveRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * 학생을 집행부에 임명
     */
    @PostMapping("/appoint")
    public ResponseEntity<Void> appointExecutive(
            @RequestParam Long studentId,
            @RequestParam Long executiveId) {
        executiveStudentService.appointExecutive(studentId, executiveId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    /**
     * 집행부 임명 해제
     */
    @PatchMapping("/appointments/{appointmentId}/retire")
    public ResponseEntity<Void> retireExecutive(@PathVariable Long appointmentId) {
        executiveStudentService.retireExecutive(appointmentId);
        return ResponseEntity.ok().build();
    }
}
