package br.com.acme.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor

public class ArticleController {

    private final ArticleService service;

    @PostMapping
    public Article create(@RequestBody final Article article) {
        return service.create(article);
    }

    @GetMapping
    public Page<Article> findAll(final Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Article findById(@PathVariable("id") final Long id) {
        return service.findById(id);
    }
}
