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
            @Param("term") final String term,
            @Param("limit") final int limit,
            @Param("offset") final long offset);

    @Query("""
            SELECT id, title, content, last_mod_date
            FROM articles
            WHERE (
                    LOWER(title) LIKE LOWER(CONCAT('%', :term1, '%'))
                    OR LOWER(content) LIKE LOWER(CONCAT('%', :term1, '%'))
                  )
               OR (
                    LOWER(title) LIKE LOWER(CONCAT('%', :term2, '%'))
                    OR LOWER(content) LIKE LOWER(CONCAT('%', :term2, '%'))
                  )
            ORDER BY last_mod_date DESC
            LIMIT :limit OFFSET :offset
            """)
    List<Article> searchByLikeTwoTerms(
            @Param("term1") final String term1,
            @Param("term2") final String term2,
            @Param("limit") final int limit,
            @Param("offset") final long offset);

    @Query("""
            SELECT id, title, content, last_mod_date
            FROM articles
            WHERE (
                    LOWER(title) LIKE LOWER(CONCAT('%', :term1, '%'))
                    OR LOWER(content) LIKE LOWER(CONCAT('%', :term1, '%'))
                  )
               OR (
                    LOWER(title) LIKE LOWER(CONCAT('%', :term2, '%'))
                    OR LOWER(content) LIKE LOWER(CONCAT('%', :term2, '%'))
                  )
               OR (
                    LOWER(title) LIKE LOWER(CONCAT('%', :term3, '%'))
                    OR LOWER(content) LIKE LOWER(CONCAT('%', :term3, '%'))
                  )
            ORDER BY last_mod_date DESC
            LIMIT :limit OFFSET :offset
            """)
    List<Article> searchByLikeThreeTerms(
            @Param("term1") final String term1,
            @Param("term2") final String term2,
            @Param("term3") final String term3,
            @Param("limit") final int limit,
            @Param("offset") final long offset);

}
