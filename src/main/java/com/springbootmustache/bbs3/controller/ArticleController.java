package com.springbootmustache.bbs3.controller;

import com.springbootmustache.bbs3.domain.Article;
import com.springbootmustache.bbs3.domain.dto.ArticleReqDto;
import com.springbootmustache.bbs3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping("/new")
    public String createArticlePage() {
        log.info("createArticlePage 실행");
        return "articles/new";
    }

    @GetMapping("")
    public String index(Model model) {
        log.info("index 실행");
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String listPage(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        log.info("listPage 실행");
        return "articles/list";
    }

    @GetMapping("/{id}")
    public String showSingle(@PathVariable Long id, Model model) {
        log.info("showSingle 실행");
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @PostMapping("/post")
    public String createArticle(ArticleReqDto articleReqDto) {
        log.info("title={}, content={}", articleReqDto.getTitle(), articleReqDto.getContent());
        Article saveArticle = articleRepository.save(articleReqDto.toEntity());
        return "redirect:/articles/" + saveArticle.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            // 에러 메세지 어떻게 나오게???
            model.addAttribute("message", String.format("id %d번이 없습니다", id));
            return "articles/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleReqDto articleReqDto, Model model) {
        log.info("title:{} content:{}", articleReqDto.getTitle(), articleReqDto.getContent());
        Article article = articleRepository.save(articleReqDto.toEntity());

        model.addAttribute("article", article);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("delete-message", String.format("id %d번이 지워졌습니다.", id));
        return "redirect:/articles";
    }


}
