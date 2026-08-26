CREATE TABLE IF NOT EXISTS articles (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(128),
    content TEXT,
    last_mod_date TIMESTAMP
);

ALTER TABLE articles
ADD COLUMN IF NOT EXISTS search_vector tsvector
GENERATED ALWAYS AS (
    setweight(
        to_tsvector('portuguese', coalesce(title, '')),
        'A'
    ) ||
    setweight(
        to_tsvector('portuguese', coalesce(content, '')),
        'B'
    )
) STORED;

CREATE INDEX IF NOT EXISTS articles_fts_prbr_idx
ON articles
USING GIN (search_vector);