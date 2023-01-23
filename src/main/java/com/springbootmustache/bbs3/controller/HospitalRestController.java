package com.springbootmustache.bbs3.controller;

import com.springbootmustache.bbs3.domain.dto.HospitalResponse;
import com.springbootmustache.bbs3.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
//        Optional<Hospital> hospital = hospitalRepository.findById(id); // Entity
//        HospitalResponse hospitalResponse = Hospital.of(hospital.get()); // DTO
        // service 도입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id);
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }
    // 결과(businessStatusName 숫자표시)
    // : {"id":2321,"roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)",
    // "hospitalName":"노소아청소년과의원","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"의원",
    // "totalAreaSize":0.0}

    // 결과(businessStatusName 상태표시)
    // {"id":2321,"roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)",
    // "hospitalName":"노소아청소년과의원","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"의원",
    // "totalAreaSize":0.0,"businessStatusName":"영업중"}


}
