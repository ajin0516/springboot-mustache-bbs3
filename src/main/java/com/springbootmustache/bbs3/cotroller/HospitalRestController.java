package com.springbootmustache.bbs3.cotroller;

import com.springbootmustache.bbs3.domain.dto.HospitalResponse;
import com.springbootmustache.bbs3.domain.entity.Hospital;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        Optional<Hospital> hospital = hospitalRepository.findById(id); // Entity
        HospitalResponse hospitalResponse = Hospital.of(hospital.get()); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }
    // 결과
// : {"id":2321,"roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)",
// "hospitalName":"노소아청소년과의원","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":
// "의원","totalAreaSize":0.0}
}
