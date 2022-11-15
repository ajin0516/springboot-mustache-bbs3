package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.Product;
import com.springbootmustache.bbs3.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
