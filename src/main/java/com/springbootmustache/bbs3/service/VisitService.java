package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.Exception.ErrorCode;
import com.springbootmustache.bbs3.Exception.HospitalReviewAppException;
import com.springbootmustache.bbs3.domain.Hospital;
import com.springbootmustache.bbs3.domain.User;
import com.springbootmustache.bbs3.domain.Visit;
import com.springbootmustache.bbs3.domain.dto.VisitCreateRequest;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import com.springbootmustache.bbs3.repository.UserRepository;
import com.springbootmustache.bbs3.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/*
hospitalId로 Hospital을 찾고, userName으로 user를 찾아서 Visit Entity를 만들어 .save()

 */
@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisit(VisitCreateRequest request, String userName) {

        // hospital 없을 때 등록 불가
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow( () -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("hospitalI:%s 가 없습니다",request.getHospitalId())));

        // user가 없을 때 등록불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow( () -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%s user가 없습니다.", userName)));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .amount(request.getAmount())
                .disease(request.getDisease())
                .build();
        visitRepository.save(visit);
    }
}
