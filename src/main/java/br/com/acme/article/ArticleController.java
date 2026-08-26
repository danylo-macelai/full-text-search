package br.com.acme.article;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService service;

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

    @GetMapping("/articles:like:morphological")
    public List<Article> searchByLikeMorphological(
            @RequestParam("term") final String term,
            final Pageable pageable) {

        return service.searchByLikeMorphological(term, pageable);
    }

}
