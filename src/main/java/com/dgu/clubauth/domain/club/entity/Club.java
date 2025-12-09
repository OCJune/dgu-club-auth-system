package com.dgu.clubauth.domain.club.entity;

import com.dgu.clubauth.domain.division.entity.Division;
import com.dgu.clubauth.domain.student.entity.Student;
import com.dgu.clubauth.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // 본 클래스가 JPA의 엔티티임을 의미
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티 필수: 기본 생성자 자동 생성, 직접 호출을 막기 위한 protected
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "club") // DB의 테이블 정의
@EntityListeners(AuditingEntityListener.class)
public class Club extends BaseEntity {
    @Id // DB의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long id; // 아이디

    @Column(name = "name", nullable = false)
    private String name; // 동아리명

    @Column(name = "designated_at", nullable = false)
    private LocalDateTime designatedAt; // 중앙동아리 등록일

    @Column(name = "professor")
    private String professor; // 지도교수 이름

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "president_id", nullable = false)
    private Student president; // 대표자 학번

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", nullable = false)
    @Setter
    private Division division; // 분과 아이디
}
