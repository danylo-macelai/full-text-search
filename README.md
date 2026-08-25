# Full-Text Search Lab

## Proposta

Pesquisar artigos pelo texto informado pelo usuário, procurando esse texto em qualquer posição do `title` ou do `content`, sem diferenciação entre maiúsculas e minúsculas, ordenando por `last_mod_date` e com paginação.

---

## Introdução

O experimento utiliza uma base de [**1.000.000 de artigos**](./src/main/resources/data.sql) para comparar desempenho e comportamento em buscas com 1, 2 e 3 termos e em variações morfológicas.

---

## Metodologia

Os testes retornam 500 artigos por vez, começando pelo primeiro resultado e ordenando os artigos pela data de última modificação, do mais recente para o mais antigo.

| Cenário | Exemplo | Resultado esperado |
|---|---|---|
| 1 termo | `pesquisa` | Artigos que contenham `pesquisa` |
| 2 termos | `pesquisa` `otimização` | Artigos que contenham um ou ambos os termos, em qualquer ordem |
| 3 termos | `pesquisa` `otimização` `documentos` | Artigos que contenham um ou mais dos três termos |
| Morfológico | `otimização` | Reconhecimento de `otimização`, `otimizar`, `otimizando`, `otimizadas`, `otimizações` e `otimizada` |

Cada cenário é avaliado em `LIKE` e FTS quanto aos resultados, tempo no PostgreSQL (`EXPLAIN ANALYZE`), tempo na aplicação Java, plano de execução e variações encontradas.

---

## LIKE

A busca com `LIKE` procura literalmente o termo informado em `title` ou `content`, ignorando diferenças entre maiúsculas e minúsculas.

Para um termo:

```sql
WHERE LOWER(title) LIKE LOWER(CONCAT('%', :term, '%'))
   OR LOWER(content) LIKE LOWER(CONCAT('%', :term, '%'))
```

Por exemplo, a busca por `pesquisa` pode encontrar:

- **pesquisa**, 
- **pesquisa***das*,
- **pesquisa***dor*,
- **pesquisa***dores*,
- **pesquisa***ndo*, 
- **pesquisa***r*,
- **pesquisa***s*;

Para múltiplos termos, o mesmo padrão é aplicado individualmente a cada termo, combinando as condições para que qualquer combinação dos termos possa ser encontrada, independentemente da ordem ou posição no artigo.

## Full-Text Search

---

## Comparação

Os cenários comparam `LIKE` e FTS quanto à quantidade de resultados, tempo de execução e plano utilizado pelo PostgreSQL.

| Cenário | Termo(s) | Estratégia | Resultados | PostgreSQL(ms) | Java (ms) | Plano |
|---|---|---|:---:|:---:|:---:|---|
| 1 termo | `pesquisa` | LIKE | 500 | 964.543 | 879.102 | `Parallel Seq Scan` |
| 1 termo | `pesquisa` | FTS | - | - | - | - |
| 2 termos | `pesquisa` `otimização` | LIKE | - | - | - | - |
| 2 termos | `pesquisa` `otimização` | FTS | - | - | - | - |
| 3 termos | `pesquisa` `otimização` `documentos` | LIKE | - | - | - | - |
| 3 termos | `pesquisa` `otimização` `documentos` | FTS | - | - | - | - |
| Morfológico | `otimização` | LIKE | - | - | - | - |
| Morfológico | `otimização` | FTS | - | - | - | - |

---

## Resultados

Os resultados apresentam, para cada cenário e estratégia, as variações encontradas nos artigos retornados e a quantidade de ocorrências de cada forma.

| Cenário | Termo(s) | Estratégia | Variações encontradas |
|---|---|---|---|
| 1 termo | `pesquisa` | LIKE | `pesquisa` (280), `pesquisadores` (215), `pesquisas` (89), `pesquisando` (64), `pesquisadas` (62), `pesquisar` (56), `pesquisador` (56) |
| 1 termo | `pesquisa` | FTS | - |
| 2 termos | `pesquisa` `otimização` | LIKE | - |
| 2 termos | `pesquisa` `otimização` | FTS | - |
| 3 termos | `pesquisa` `otimização` `documentos` | LIKE | - |
| 3 termos | `pesquisa` `otimização` `documentos` | FTS | - |
| Morfológico | `otimização` | LIKE | - |
| Morfológico | `otimização` | FTS | - |

---

## Conclusão

---

## Execução

### Pré-requisitos

- Java 17
- Docker
- Docker Compose

```bash
docker compose build
docker compose up -d
docker compose ps
```

### Executar:

```bash
curl --location "http://localhost:8080/articles:like:one?term=pesquisa"
```

### Remover ambiente e dados:

```bash
docker compose down --rmi all --volumes --remove-orphans
```

---

## Referências

[1] SPRING, Spring Initializr.
https://start.spring.io/

[2] SPRING, Spring Data JDBC.
https://spring.io/projects/spring-data-jdbc

[3] POSTGRESQL GLOBAL DEVELOPMENT GROUP, Full Text Search.
https://www.postgresql.org/docs/current/textsearch.html