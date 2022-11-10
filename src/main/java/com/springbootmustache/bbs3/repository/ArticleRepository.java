package com.springbootmustache.bbs3.repository;

import com.springbootmustache.bbs3.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
