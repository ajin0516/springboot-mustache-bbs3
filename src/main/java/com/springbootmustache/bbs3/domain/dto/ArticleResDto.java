package com.springbootmustache.bbs3.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleResDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public ArticleResDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
