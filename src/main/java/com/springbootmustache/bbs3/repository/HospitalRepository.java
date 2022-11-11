package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
