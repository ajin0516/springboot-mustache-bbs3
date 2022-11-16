package com.springbootmustache.bbs3.cotroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmustache.bbs3.domain.dto.ArticleReqDto;
import com.springbootmustache.bbs3.domain.dto.ArticleResDto;
import com.springbootmustache.bbs3.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
//
//    obj
    @MockBean
    ArticleService articleService;



    @Test
    @DisplayName("해당 id의 글이 잘 나오는지")
    void findSingle() throws Exception {

        ArticleResDto articleResDto = ArticleResDto.builder()
                .title("첫")
                .content("내용")
                .build();
        Long id = 13L;

        given(articleService.getArticleById(id))
                .willReturn(articleResDto);

        String url = String.format("/api/v1/articles/13");
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(id);
    }

    @Test
    @DisplayName("글 등록이 잘되는지")
    void add( ) throws Exception {
        // 1. RequestDto요청을 받는다
        ArticleReqDto articleReq = ArticleReqDto.builder()
                .title("제목")
                .content("내용")
                .build();
        // 요청 -> 서비스 로직 실행 -> 응답

        given(articleService.add(any(ArticleReqDto.class)))
                .willReturn(new ArticleResDto(1L,articleReq.getTitle(),articleReq.getContent()));

        // 3. responsedto로 응답
        mockMvc.perform(post ("/api/v1/articles/post")
                        .content(objectMapper.writeValueAsBytes(articleReq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andDo(print());

//       verify(articleService).saveArticle(ArgumentMatchers.refEq(articleRequestDto));
    }
}