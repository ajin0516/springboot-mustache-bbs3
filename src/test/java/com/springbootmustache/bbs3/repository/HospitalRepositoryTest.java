package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.dto.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void name(){
        Optional<Hospital> hospitalById = hospitalRepository.findById(1);
        Hospital hp = hospitalById.get();
        System.out.println(hp.getId());
        assertEquals(1,hp.getId());

    }
}