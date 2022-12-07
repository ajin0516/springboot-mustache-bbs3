package com.springbootmustache.bbs3.service;

import com.springbootmustache.bbs3.domain.Article;
import com.springbootmustache.bbs3.domain.dto.ArticleReqDto;
import com.springbootmustache.bbs3.domain.dto.ArticleResDto;
import com.springbootmustache.bbs3.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResDto getArticleById(Long id){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        ArticleResDto articleDto = Article.of(optionalArticle.get());
       return articleDto;
    }

    public ArticleResDto add(ArticleReqDto articlereqDto){
        // 1. 요청을 받는다
        Article article = articlereqDto.toEntity();
        // 2. 디비에 저장한다
        articleRepository.save(article);
        // 3. 응답을 한다
        ArticleResDto resDto = Article.of(article);

        return resDto;
    }
//    public ArticleAddResponse add(ArticleAddRequest dto) {
//        Article article = dto.toEntity();
//        Article savedArticle = articleRepository.save(article);
//        return new ArticleAddResponse(savedArticle.getId(),
//                savedArticle.getTitle(),
//                savedArticle.getContent());
//    }

    public Page<ArticleResDto> getAll(Pageable pageable) {
        Page<Article> page = articleRepository.findAll(pageable);
        return page.map(Article::of);
    }
}
