package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.domain.Hospital;
import com.springbootmustache.bbs3.domain.Review;
import com.springbootmustache.bbs3.domain.dto.ReviewCreateRequest;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import com.springbootmustache.bbs3.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;


    public Review get(Long id) {
        Optional<Review> reviews = reviewRepository.findById(id);
        return reviews.get();
    }

    public Page<Review> getReview(Integer hospitalId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByHospitalId(hospitalId, pageable);
        return  reviews;
    }

    public void create(ReviewCreateRequest request) {
        // hospitalId를 받아서 Hospital을 select함
        Optional<Hospital> optionalHospital = hospitalRepository.findById(request.getHospitalId());
        Review review = reviewRepository.save(Review.of(
                optionalHospital.orElseThrow(() -> new IllegalArgumentException("해당 hospitalId에 일치하는 병원이 없습니다")),
                request.getTitle(), request.getContent(), request.getUserName()));
    }

}
