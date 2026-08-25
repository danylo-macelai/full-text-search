package br.com.acme.article;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository
        extends ListCrudRepository<Article, Long>,
        ListPagingAndSortingRepository<Article, Long> {

    @Query("""
            SELECT id, title, content, last_mod_date
            FROM articles
            WHERE LOWER(title) LIKE LOWER(CONCAT('%', :term, '%'))
               OR LOWER(content) LIKE LOWER(CONCAT('%', :term, '%'))
            ORDER BY last_mod_date DESC
            LIMIT :limit OFFSET :offset
            """)
    List<Article> searchByLikeOneTerm(
            @Param("term") String term,
            @Param("limit") int limit,
            @Param("offset") long offset);

}
