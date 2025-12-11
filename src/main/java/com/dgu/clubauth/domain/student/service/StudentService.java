package com.dgu.clubauth.domain.student.service;

import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.enums.Gender;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Long studentId, String name, String deptName, String phoneNum, Gender gender) {
        // 1. Student 엔티티 객체 생성
        Student newStudent = Student.builder()
                .id(studentId) // 학번을 PK로 사용
                .name(name)
                .deptName(deptName)
                .phoneNum(phoneNum)
                .gender(gender)
                .build();

        // 2. 표준 JPA save()를 통해 DB에 INSERT
        return studentRepository.save(newStudent);
    }


}