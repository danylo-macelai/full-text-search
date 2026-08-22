package br.com.acme.article;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ArticleRepository
        extends ListCrudRepository<Article, Long>,
        ListPagingAndSortingRepository<Article, Long> {

}
