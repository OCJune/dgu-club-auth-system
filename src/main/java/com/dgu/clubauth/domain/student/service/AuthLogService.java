package com.dgu.clubauth.domain.student.service;

import com.dgu.clubauth.domain.student.entity.AuthLog;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.domain.student.enums.Type;
import com.dgu.clubauth.domain.student.repository.AuthLogRepository;
import com.dgu.clubauth.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthLogService {

    private final AuthLogRepository authLogRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public AuthLog createAuthLog(Boolean success, Type type, Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("학번: " +  studentId + "에 해당하는 학생을 찾을 수 없습니다."));

        AuthLog newAuthLog = AuthLog.builder()
                .success(success)
                .type(type)
                .student(student)
                .build();

        return authLogRepository.save(newAuthLog);
    }
}
