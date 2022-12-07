package com.springbootmustache.bbs3.domain;

import com.springbootmustache.bbs3.domain.dto.ArticleResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "article3")
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;


    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    /*
        요청-> 디비저장 -> 응답
        ArticleReqDto -> article(entity) -> ArticleResDto
        Article(entity) -> ArticleResDto
        안정성 보장
      */

    public static ArticleResDto of(Article article){
        ArticleResDto articleResDto = new ArticleResDto(article.getId(), article.getTitle(), article.getContent());
        return articleResDto;
    }
}
