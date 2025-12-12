package com.dgu.clubauth.domain.student.controller;

import com.dgu.clubauth.domain.student.dto.StudentRequest;
import com.dgu.clubauth.domain.student.dto.StudentResponse;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import com.dgu.clubauth.domain.student.service.StudentService;
import com.dgu.clubauth.global.exception.DuplicateResourceException;
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
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Student", description = "학생 관리 API")
public class StudentController {
    
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    
    /**
     * 학생 등록
     */
    @PostMapping
    @Operation(summary = "학생 등록", description = "새로운 학생을 등록합니다. 학번은 직접 지정합니다.")
    public ResponseEntity<StudentResponse> createStudent(@jakarta.validation.Valid @RequestBody StudentRequest.Create request) {
        // 중복 학번 체크
        if (studentRepository.existsById(request.getId())) {
            throw new DuplicateResourceException("이미 등록된 학번입니다: " + request.getId());
        }
        
        Student student = studentService.createStudent(
                request.getId(),
                request.getName(),
                request.getDeptName(),
                request.getPhoneNum(),
                request.getGender()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(StudentResponse.from(student));
    }
    
    /**
     * 학생 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentRepository.findAll().stream()
                .map(StudentResponse::from)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(students);
    }
    
    /**
     * 학생 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("학생", id));
        
        return ResponseEntity.ok(StudentResponse.from(student));
    }
    
    /**
     * 학생 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("학생", id);
        }
        
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
