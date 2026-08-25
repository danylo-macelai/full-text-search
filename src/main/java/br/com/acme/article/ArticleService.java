package br.com.acme.article;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public Article create(final Article article) {
        return repository.save(article);
    }

    public Page<Article> findAll(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Article findById(final Long id) {
        return repository.findById(id)
                .orElseThrow();
    }
    
    public List<Article> searchByLikeOneTerm(
            final String term,
            final Pageable pageable) {

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByLikeOneTerm(term, limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=LIKE | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                term,
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

}