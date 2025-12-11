package com.dgu.clubauth.domain.division.entity;

import com.dgu.clubauth.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // 본 클래스가 JPA의 엔티티임을 의미
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티 필수: 기본 생성자 자동 생성, 직접 호출을 막기 위한 protected
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "division") // DB의 테이블 정의
@EntityListeners(AuditingEntityListener.class)
public class Division extends BaseEntity {
    @Id // DB의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 자동 생성
    private Long id; // 아이디

    @Column(name = "name", nullable = false)
    private String name; // 분과명
}
