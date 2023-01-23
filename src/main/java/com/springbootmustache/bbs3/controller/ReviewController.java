package com.springbootmustache.bbs3.controller;


import com.springbootmustache.bbs3.domain.Review;
import com.springbootmustache.bbs3.domain.dto.ReviewCreateRequest;
import com.springbootmustache.bbs3.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/{id}")
    public ResponseEntity<Review> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(reviewService.get(id));
    }

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody ReviewCreateRequest request) {
        reviewService.create(request);
        return ResponseEntity.ok().body(null);
    }
}
