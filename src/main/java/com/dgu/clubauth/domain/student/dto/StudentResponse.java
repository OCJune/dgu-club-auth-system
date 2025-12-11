package com.dgu.clubauth.domain.student.dto;

import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id; // 학번
    private String name; // 학생 이름
    private String deptName; // 소속 학과(부)
    private String phoneNum; // 연락처
    private Gender gender; // 성별
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static StudentResponse from(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .deptName(student.getDeptName())
                .phoneNum(student.getPhoneNum())
                .gender(student.getGender())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
