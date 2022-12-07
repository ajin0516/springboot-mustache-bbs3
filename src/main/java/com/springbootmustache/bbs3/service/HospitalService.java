package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.domain.dto.HospitalResponse;
import com.springbootmustache.bbs3.domain.entity.Hospital;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id); //Entity
        Hospital hospital = optionalHospital.get();
        HospitalResponse hospitalResponse = hospital.of(hospital); // DTO


        // HospitalResponse에 이 로직을 넣을 수 있음
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        }else{
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;



//        switch (hospital.getBusinessStatusCode()){
//            case 13: hospitalResponse.setBusinessStatusName("영업중"); break;
//            case 3: hospitalResponse.setBusinessStatusName("폐업"); break;
//            case 4: hospitalResponse.setBusinessStatusName(); break;
//            case 24: hospitalResponse.setBusinessStatusName(); break;
//        }


    }
}
