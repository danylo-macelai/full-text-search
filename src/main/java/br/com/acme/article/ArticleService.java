package br.com.acme.article;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

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

    public List<Article> searchByLikeTwoTerms(
            final String[] terms,
            final Pageable pageable) {

        if (terms.length != 2) {
            throw new IllegalArgumentException("São necessários exatamente 2 termos");
        }

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByLikeTwoTerms(terms[0], terms[1], limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=LIKE | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                String.join(", ", terms),
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

    public List<Article> searchByLikeThreeTerms(
            final String[] terms,
            final Pageable pageable) {

        if (terms.length != 3) {
            throw new IllegalArgumentException("São necessários exatamente 3 termos");
        }

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByLikeThreeTerms(terms[0], terms[1], terms[2], limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=LIKE | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                String.join(", ", terms),
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

    public List<Article> searchByLikeMorphological(
            final String term,
            final Pageable pageable) {

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByLikeOneTerm(
                term,
                limit,
                offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=LIKE | type=MORPHOLOGICAL | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                term,
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

    public List<Article> searchByFtsOneTerm(
            final String term,
            final Pageable pageable) {

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByFtsOneTerm(term, limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=FTS | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                term,
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

    public List<Article> searchByFtsTwoTerms(
            final String[] terms,
            final Pageable pageable) {

        if (terms.length != 2) {
            throw new IllegalArgumentException("São necessários exatamente 2 termos");
        }

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByFtsTwoTerms(terms[0], terms[1], limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=FTS | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                String.join(", ", terms),
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

    public List<Article> searchByFtsThreeTerms(
            final String[] terms,
            final Pageable pageable) {

        if (terms.length != 3) {
            throw new IllegalArgumentException("São necessários exatamente 3 termos");
        }

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByFtsThreeTerms(terms[0], terms[1], terms[2], limit, offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=FTS | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                String.join(", ", terms),
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }
    
    
    public List<Article> searchByFtsMorphological(
            final String term,
            final Pageable pageable) {

        var start = System.nanoTime();

        var limit = pageable.getPageSize();
        var offset = pageable.getOffset();

        var result = repository.searchByFtsOneTerm(
                term,
                limit,
                offset);

        var elapsed = System.nanoTime() - start;

        System.out.printf(
                "SEARCH | strategy=FTS | type=MORPHOLOGICAL | term='%s' | page=%d | size=%d | offset=%d | results=%d | elapsed=%.3f ms%n",
                term,
                pageable.getPageNumber() + 1,
                pageable.getPageSize(),
                pageable.getOffset(),
                result.size(),
                elapsed / 1_000_000.0);

        return result;
    }

}