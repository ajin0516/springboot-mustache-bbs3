package com.springbootmustache.bbs3.cotroller;


import com.springbootmustache.bbs3.domain.dto.ArticleReqDto;
import com.springbootmustache.bbs3.domain.dto.ArticleResDto;
import com.springbootmustache.bbs3.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@Slf4j
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResDto> getArticle(@PathVariable Long id) {
        ArticleResDto articleById = articleService.getArticleById(id);
        return ResponseEntity.ok().body(articleById);
    }

    // 요청 -> 컨트롤러 -> 서비스로직 //  -> 레파지토리 -> 디비
    @PostMapping("/post")
    public ResponseEntity<ArticleResDto> postArticleDto(@RequestBody ArticleReqDto articleReqDto) {
        ArticleResDto articleResDto = articleService.add(articleReqDto);
        log.info("글쓰기 요청. title ={}, content={}",articleReqDto.getTitle(),articleReqDto.getContent());
        return ResponseEntity.ok().body(articleResDto);
    }

}
