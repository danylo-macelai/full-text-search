package br.com.acme.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

    @PostMapping("/articles")
    public Article create(@RequestBody final Article article) {
        return service.create(article);
    }

    @GetMapping("/articles")
    public Page<Article> findAll(final Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/articles/{id}")
    public Article findById(@PathVariable("id") final Long id) {
        return service.findById(id);
    }

    @GetMapping("/articles:like:one")
    public List<Article> searchByLikeOneTerm(
            @RequestParam("term") final String term,
            final Pageable pageable) {

        return service.searchByLikeOneTerm(term, pageable);
    }

    @GetMapping("/articles:like:two")
    public List<Article> searchByLikeTwoTerms(
            @RequestParam("terms") final String[] terms,
            final Pageable pageable) {

        return service.searchByLikeTwoTerms(terms, pageable);
    }

    @GetMapping("/articles:like:three")
    public List<Article> searchByLikeThreeTerms(
            @RequestParam("terms") final String[] terms,
            final Pageable pageable) {

        return service.searchByLikeThreeTerms(terms, pageable);
    }
}
