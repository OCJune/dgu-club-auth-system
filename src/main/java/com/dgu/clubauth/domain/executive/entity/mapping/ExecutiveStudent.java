package com.dgu.clubauth.domain.executive.entity.mapping;

import com.dgu.clubauth.domain.executive.entity.Executive;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.global.entity.BaseEntity;
import com.dgu.clubauth.global.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // 본 클래스가 JPA의 엔티티임을 의미
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티 필수: 기본 생성자 자동 생성, 직접 호출을 막기 위한 protected
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "executive_student") // DB의 테이블 정의
@EntityListeners(AuditingEntityListener.class)
public class ExecutiveStudent extends BaseEntity {
    @Id // DB의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long id; // 아이디

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING) // Enum을 사용 시 데이터 형태 명시
    @Builder.Default
    @Setter
    private Status status = Status.ACTIVE; // 상태

    @Column(name = "inactivated_at")
    @Setter
    private LocalDateTime inactivatedAt; // 비활성화일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "executive_id", nullable = false)
    private Executive executive; // 직책 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // 학번
}
