package com.dgu.clubauth.domain.student.entity;

import com.dgu.clubauth.domain.student.enums.Type;
import com.dgu.clubauth.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // 본 클래스가 JPA의 엔티티임을 의미
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티 필수: 기본 생성자 자동 생성, 직접 호출을 막기 위한 protected
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "auth_log") // DB의 테이블 정의
@EntityListeners(AuditingEntityListener.class)
public class AuthLog extends BaseEntity {
    @Id // DB의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long id; // 아이디

    @Column(name = "success", nullable = false)
    private Boolean success; // 성공 여부

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING) // Enum을 사용 시 데이터 형태 명시
    private Type type; // 인증 방식

    @ManyToOne(fetch = FetchType.LAZY) // auth_log와 student가 N:1 관계
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // 외래키: 학번
}
