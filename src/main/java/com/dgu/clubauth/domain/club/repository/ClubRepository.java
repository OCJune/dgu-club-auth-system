package com.dgu.clubauth.domain.club.repository;

import com.dgu.clubauth.domain.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
