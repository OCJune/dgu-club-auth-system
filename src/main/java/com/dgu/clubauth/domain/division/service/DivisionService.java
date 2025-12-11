package com.dgu.clubauth.domain.division.service;

import com.dgu.clubauth.domain.division.entity.Division;
import com.dgu.clubauth.domain.division.repository.DivisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DivisionService {

    private final DivisionRepository divisionRepository;

    @Transactional
    public Division createDivision(String name) {
        Division newDivision = Division.builder()
                .name(name)
                .build();

        return divisionRepository.save(newDivision);
    }
}
