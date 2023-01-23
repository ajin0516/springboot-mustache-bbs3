package com.springbootmustache.bbs3.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String userName;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public static Review of(Hospital hospital, String title, String content, String userName) {
        Review review = new Review();
        review.setHospital(hospital);
        review.setTitle(title);
        review.setContent(content);
        review.setUserName(userName);
        return review;
    }

}
