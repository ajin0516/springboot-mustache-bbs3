package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건지료소인 데이터가 잘 나오는지")
    void BusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
        System.out.println(hospitals.size());
    }

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getPatientRoomCount(), hospital.getTotalAreaSize());
        }

        System.out.println(hospitals.size());
    }

    @Test
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void startWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("경희"); // 가톨릭 서울 연세 결희
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void endsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndsWith("병원"); //의원, 병원, 이비인후과, 치과
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void findByPatientRoomCountGreaterThanAndPatientRoomCount() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void patientRoomCount(){
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetween(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void orderByPatientRoomCount() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void name(){
        Optional<Hospital> hospitalById = hospitalRepository.findById(1);
        Hospital hp = hospitalById.get();
        System.out.println(hp.getId());
        assertEquals(1,hp.getId());

    }
}