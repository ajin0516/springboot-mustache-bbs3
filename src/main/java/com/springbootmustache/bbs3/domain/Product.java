package com.springbootmustache.bbs3.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product") // 생략가능
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column
    private Integer stock;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
