package br.com.acme.article;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    private Long id;

    private String title;

    private String content;

    @Column("last_mod_date")
    private LocalDateTime lastModifiedAt;

}
