package com.dgu.clubauth.domain.student.entity;

import com.dgu.clubauth.domain.student.enums.Gender;
import com.dgu.clubauth.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // 본 클래스가 JPA의 엔티티임을 의미
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티 필수: 기본 생성자 자동 생성, 직접 호출을 막기 위한 protected
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "student") // DB의 테이블 정의
@EntityListeners(AuditingEntityListener.class)
public class Student extends BaseEntity {
    @Id // DB의 Primary Key
    private Long id; // 학번

    @Column(name = "name", nullable = false) // DB의 Attribute
    private String name; // 학생 이름

    @Column(name = "dept_name", nullable = false)
    private String dept_name; // 소속 학과(부)

    @Column(name = "phone_num", nullable = false)
    private String phone_num; // 연락처

    @Column(name = "gender")
    @Enumerated(EnumType.STRING) // Enum을 사용 시 데이터 형태 명시
    private Gender gender; // 성별
}
