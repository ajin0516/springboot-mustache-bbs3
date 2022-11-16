package com.springbootmustache.bbs3.domain;

import com.springbootmustache.bbs3.domain.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "article3")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public static ArticleDto of(Article article){
        return new ArticleDto(article.getId(),
                article.getTitle(),
                article.getContent());
    }
}
