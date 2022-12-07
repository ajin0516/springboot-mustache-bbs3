package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.domain.dto.HospitalResponse;
import com.springbootmustache.bbs3.domain.entity.Hospital;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void beforeEach() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("영업 상태코드에 맞게 잘 나오는지")
    void getStatus() {
        Hospital hospital = Hospital.builder()
                .id(1)
                .businessStatusCode(3)
                .build();

        Hospital hospital1 = Hospital.builder()
                .id(29)
                .businessStatusCode(13)
                .build();
        when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital));
        when(hospitalRepository.findById(29)).thenReturn(Optional.of(hospital1));
        HospitalResponse closeHospital = hospitalService.getHospital(1);
        HospitalResponse openHospital = hospitalService.getHospital(29);

//        assertEquals("폐업",hospitalRepository.findById(3).get().getBusinessStatusCode());
        assertEquals("폐업",closeHospital.getBusinessStatusName());
        assertEquals("영업중",openHospital.getBusinessStatusName());
    }
}