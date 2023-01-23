package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByHospitalId(Integer hospitalId, Pageable pageable);
}
