package com.springbootmustache.bbs3.domain.dto;

import com.springbootmustache.bbs3.domain.Article;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Setter

public class ArticleReqDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public ArticleReqDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
    /*
        reqdto -> entity로
     */
    public Article toEntity(){
        return new Article(id, title, content);
    }

}
