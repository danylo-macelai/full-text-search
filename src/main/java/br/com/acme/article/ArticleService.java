package br.com.acme.article;

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
}
