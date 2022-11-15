package com.springbootmustache.bbs3.cotroller;

import com.springbootmustache.bbs3.domain.Article;
import com.springbootmustache.bbs3.domain.entity.Hospital;
import com.springbootmustache.bbs3.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("")
    public String list(@PageableDefault(size = 10,sort = "id",direction = Sort.Direction.ASC) Model model, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        log.info("size:{}", hospitals.getSize());
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", (pageable).previousOrFirst().getPageNumber());
        model.addAttribute("next", (pageable).next().getPageNumber());
        return "hospitals/list";
    }

    @GetMapping("/{id}")
    public String select(@PathVariable Integer id, Model model) {
        Optional<Hospital> optionalArticle = hospitalRepository.findById(id);
        if(!optionalArticle.isEmpty()) {
            model.addAttribute("hospital", optionalArticle.get());
            return "hospitals/show";
        }else {
            model.addAttribute("articles",String.format("id %d번이 없습니다",id));
            return "articles/error";
        }
    }

}
