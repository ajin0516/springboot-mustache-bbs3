package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
