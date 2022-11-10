package com.springbootmustache.bbs3.cotroller;

import com.springbootmustache.bbs3.domain.Article;
import com.springbootmustache.bbs3.domain.dto.ArticleDto;
import com.springbootmustache.bbs3.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("")
    public String listPage(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles",articles);
        return "articles/list";
    }

    @GetMapping("/new")
    public String createArticlePage(){
        return "new";
    }

    @GetMapping("/{id}")
    public String showSingle(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()){
            model.addAttribute("article",optionalArticle.get());
            return "articles/show";
        }else {
            return "articles/error";
        }
    }

    @PostMapping("")
    public String createArticle(ArticleDto articleDto){
        log.info(articleDto.getTitle());
        Article saveArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d",saveArticle.getId());
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        }else {
            // 에러 메세지 어떻게 나오게???
            model.addAttribute("message",String.format("id %d번이 없습니다",id));
            return "articles/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());

        model.addAttribute("article",article);
        return String.format("redirect:/articles/%d",article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        articleRepository.deleteById(id);
        model.addAttribute("delete-message",String.format("id %d번이 지워졌습니다.",id));
        return "redirect:/articles";
    }

}
