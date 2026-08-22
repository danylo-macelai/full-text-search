CREATE TABLE IF NOT EXISTS articles (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(128),
    content TEXT,
    last_mod_date TIMESTAMP
);