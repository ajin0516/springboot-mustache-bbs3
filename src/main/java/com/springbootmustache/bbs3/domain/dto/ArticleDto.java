package com.springbootmustache.bbs3.domain.dto;

import com.springbootmustache.bbs3.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id,title, content);
    }
}
