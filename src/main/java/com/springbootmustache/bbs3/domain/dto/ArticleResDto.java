package com.springbootmustache.bbs3.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResDto {

    private Long id;
    private String title;
    private String content;

}
