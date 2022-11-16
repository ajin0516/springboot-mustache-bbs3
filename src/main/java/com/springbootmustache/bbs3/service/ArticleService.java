package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.domain.Article;
import com.springbootmustache.bbs3.domain.dto.ArticleDto;
import com.springbootmustache.bbs3.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;

    }

    public ArticleDto getArticleById(Long id){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        ArticleDto articleDto = Article.of(optionalArticle.get());
       return articleDto;

    }
}
