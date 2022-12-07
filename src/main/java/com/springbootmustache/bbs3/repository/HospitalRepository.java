package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypeName);
    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(int var1, int var2); // 10~20개 사이의 병상수
    List<Hospital> findByPatientRoomCountBetween(Integer startCount, Integer endCount); // 10~20개 사이의 병상수

    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int a, int b); // 내림차순 - 오름차순(asc)

    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable);
}
