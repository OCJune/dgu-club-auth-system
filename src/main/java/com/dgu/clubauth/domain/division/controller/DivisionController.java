package com.dgu.clubauth.domain.division.controller;

import com.dgu.clubauth.domain.division.dto.DivisionRequest;
import com.dgu.clubauth.domain.division.dto.DivisionResponse;
import com.dgu.clubauth.domain.division.entity.Division;
import com.dgu.clubauth.domain.division.repository.DivisionRepository;
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
@RequestMapping("/api/divisions")
@RequiredArgsConstructor
@Tag(name = "Division", description = "분과 관리 API")
public class DivisionController {
    
    private final DivisionRepository divisionRepository;
    
    /**
     * 분과 등록
     */
    @PostMapping
    @Operation(summary = "분과 등록", description = "새로운 분과를 등록합니다.")
    public ResponseEntity<DivisionResponse> createDivision(@RequestBody DivisionRequest.Create request) {
        Division division = Division.builder()
                .name(request.getName())
                .build();
        
        Division savedDivision = divisionRepository.save(division);
        return ResponseEntity.status(HttpStatus.CREATED).body(DivisionResponse.from(savedDivision));
    }
    
    /**
     * 분과 목록 조회
     */
    @GetMapping
    @Operation(summary = "분과 목록 조회", description = "모든 분과의 목록을 조회합니다.")
    public ResponseEntity<List<DivisionResponse>> getAllDivisions() {
        List<DivisionResponse> divisions = divisionRepository.findAll().stream()
                .map(DivisionResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(divisions);
    }
    
    /**
     * 분과 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<DivisionResponse> getDivision(@PathVariable Long id) {
        Division division = divisionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("분과", id));
        
        return ResponseEntity.ok(DivisionResponse.from(division));
    }
    
    /**
     * 분과 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Long id) {
        if (!divisionRepository.existsById(id)) {
            throw new ResourceNotFoundException("분과", id);
        }
        
        divisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
