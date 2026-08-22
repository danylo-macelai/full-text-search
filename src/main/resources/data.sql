INSERT INTO articles (
    title,
    content,
    last_mod_date
)
SELECT
    data.title || ' ' || g,
    data.content || ' ' || g,
    CURRENT_TIMESTAMP - ((g % 365) * INTERVAL '1 day')
-- GERA OS VALORES DE 1 ATÉ 100.000 SOMENTE QUANDO A TABELA "ARTICLES" ESTIVER VAZIA.
FROM generate_series(
    1,
    CASE
        -- VERIFICA SE JÁ EXISTE PELO MENOS UM ARTIGO NA TABELA.
        WHEN EXISTS (SELECT 1 FROM articles LIMIT 1)
		-- SE JÁ EXISTIR PELO MENOS UM REGISTRO EM "ARTICLES", O LIMITE PASSA A SER 0.
		-- GENERATE_SERIES(1, 0) RETORNA ZERO LINHAS, FAZENDO COM QUE NENHUM ARTIGO SEJA INSERIDO.
        THEN 0

        -- TABELA VAZIA: GERA 100.000 VALORES.
        -- COMO EXISTEM 10 FAMÍLIAS NO CROSS JOIN LATERAL,
        -- SERÃO GERADOS:
        -- 100.000 × 10 (FAMÍLIAS) = 1.000.000 ARTIGOS.
        ELSE 100000
    END
) AS g
CROSS JOIN LATERAL (
    VALUES
        -- 1. Família Pesquisa
        (
            CASE g % 7
                WHEN 0 THEN 'Pesquisa de informações'
                WHEN 1 THEN 'Pesquisar informações'
                WHEN 2 THEN 'Pesquisando informações'
                WHEN 3 THEN 'Informações pesquisadas'
                WHEN 4 THEN 'Pesquisador de informações'
                WHEN 5 THEN 'Pesquisadores de informações'
                ELSE 'Pesquisas de informações'
            END,

            CASE g % 7
                WHEN 0 THEN
                    'A pesquisa das informações permite recuperar documentos relevantes para a análise dos resultados.'
                WHEN 1 THEN
                    'Os pesquisadores precisam pesquisar as informações disponíveis antes de analisar os documentos.'
                WHEN 2 THEN
                    'Os pesquisadores estão pesquisando novas informações para encontrar documentos relevantes.'
                WHEN 3 THEN
                    'As informações pesquisadas foram utilizadas durante a análise dos documentos encontrados.'
                WHEN 4 THEN
                    'O pesquisador analisou as informações recuperadas durante a pesquisa dos documentos.'
                WHEN 5 THEN
                    'Os pesquisadores realizaram diferentes pesquisas para recuperar informações relevantes.'
                ELSE
                    'As pesquisas realizadas permitiram encontrar informações importantes nos documentos analisados.'
            END
        ),
        -- 2. Família Análise
        (
            CASE g % 6
                WHEN 0 THEN 'Análise de dados'
                WHEN 1 THEN 'Analisar dados'
                WHEN 2 THEN 'Analisando dados'
                WHEN 3 THEN 'Dados analisados'
                WHEN 4 THEN 'Analista de dados'
                ELSE 'Análises de dados'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'A análise dos dados revelou informações importantes sobre os documentos armazenados.'
                WHEN 1 THEN
                    'Os pesquisadores precisam analisar os dados antes de apresentar os resultados.'
                WHEN 2 THEN
                    'A equipe está analisando os dados coletados durante o processamento das informações.'
                WHEN 3 THEN
                    'Os dados analisados apresentaram padrões semelhantes durante a pesquisa.'
                WHEN 4 THEN
                    'O analista avaliou os dados para identificar informações relevantes.'
                ELSE
                    'As análises realizadas apresentaram resultados diferentes durante os testes.'
            END
        ),
        -- 3. Família Otimização
        (
            CASE g % 6
                WHEN 0 THEN 'Otimização de consultas'
                WHEN 1 THEN 'Otimizar consultas'
                WHEN 2 THEN 'Otimizando consultas'
                WHEN 3 THEN 'Consultas otimizadas'
                WHEN 4 THEN 'Otimizações de consultas'
                ELSE 'Consulta otimizada'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'A otimização das consultas reduziu o tempo necessário para recuperar informações.'
                WHEN 1 THEN
                    'A equipe precisou otimizar as consultas para melhorar o desempenho do sistema.'
                WHEN 2 THEN
                    'Os desenvolvedores estão otimizando as consultas executadas pelo banco de dados.'
                WHEN 3 THEN
                    'As consultas otimizadas apresentaram melhor desempenho durante os testes.'
                WHEN 4 THEN
                    'Diferentes otimizações foram realizadas para reduzir o tempo de resposta.'
                ELSE
                    'A consulta otimizada recuperou os documentos de forma mais eficiente.'
            END
        ),
        -- 4. Família Indexação
        (
            CASE g % 7
                WHEN 0 THEN 'Indexação de documentos'
                WHEN 1 THEN 'Indexar documentos'
                WHEN 2 THEN 'Indexando documentos'
                WHEN 3 THEN 'Documentos indexados'
                WHEN 4 THEN 'Índice de documentos'
                WHEN 5 THEN 'Índices de documentos'
                ELSE 'Indexações de documentos'
            END,

            CASE g % 7
                WHEN 0 THEN
                    'A indexação dos documentos permite recuperar informações de forma mais eficiente.'
                WHEN 1 THEN
                    'Os desenvolvedores precisam indexar os documentos antes de executar as consultas.'
                WHEN 2 THEN
                    'O sistema está indexando novos documentos durante o processamento das informações.'
                WHEN 3 THEN
                    'Os documentos indexados podem ser encontrados rapidamente durante uma pesquisa.'
                WHEN 4 THEN
                    'O índice criado para os documentos melhorou o desempenho das consultas.'
                WHEN 5 THEN
                    'Os índices utilizados pelo sistema permitem recuperar informações rapidamente.'
                ELSE
                    'As indexações realizadas melhoraram o desempenho da recuperação dos documentos.'
            END
        ),
        -- 5. Família Processamento
        (
            CASE g % 6
                WHEN 0 THEN 'Processamento de dados'
                WHEN 1 THEN 'Processar dados'
                WHEN 2 THEN 'Processando dados'
                WHEN 3 THEN 'Dados processados'
                WHEN 4 THEN 'Processamento realizado'
                ELSE 'Processamentos de dados'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'O processamento dos dados foi realizado antes da recuperação das informações.'
                WHEN 1 THEN
                    'A aplicação precisa processar os dados antes de armazená-los no banco.'
                WHEN 2 THEN
                    'O sistema está processando grandes volumes de informações diariamente.'
                WHEN 3 THEN
                    'Os dados processados foram utilizados durante as consultas realizadas.'
                WHEN 4 THEN
                    'O processamento realizado apresentou resultados consistentes durante os testes.'
                ELSE
                    'Diferentes processamentos foram executados para avaliar o desempenho do sistema.'
            END
        ),
        -- 6. Família Armazenamento
        (
            CASE g % 6
                WHEN 0 THEN 'Armazenamento de informações'
                WHEN 1 THEN 'Armazenar informações'
                WHEN 2 THEN 'Armazenando informações'
                WHEN 3 THEN 'Informações armazenadas'
                WHEN 4 THEN 'Armazenamento realizado'
                ELSE 'Armazenamentos de informações'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'O armazenamento das informações foi realizado após o processamento dos documentos.'
                WHEN 1 THEN
                    'A aplicação precisa armazenar as informações recuperadas durante a pesquisa.'
                WHEN 2 THEN
                    'O sistema está armazenando novas informações no banco de dados.'
                WHEN 3 THEN
                    'As informações armazenadas podem ser recuperadas por meio das consultas.'
                WHEN 4 THEN
                    'O armazenamento realizado apresentou bom desempenho durante os testes.'
                ELSE
                    'Diferentes armazenamentos foram avaliados durante o desenvolvimento do sistema.'
            END
        ),
        -- 7. Família Desenvolvimento
        (
            CASE g % 6
                WHEN 0 THEN 'Desenvolvimento de aplicações'
                WHEN 1 THEN 'Desenvolver aplicações'
                WHEN 2 THEN 'Desenvolvendo aplicações'
                WHEN 3 THEN 'Aplicações desenvolvidas'
                WHEN 4 THEN 'Desenvolvedor de aplicações'
                ELSE 'Desenvolvedores de aplicações'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'O desenvolvimento da aplicação começou após a análise dos requisitos.'
                WHEN 1 THEN
                    'Os desenvolvedores precisam desenvolver aplicações capazes de processar informações.'
                WHEN 2 THEN
                    'A equipe está desenvolvendo uma aplicação para consultar documentos.'
                WHEN 3 THEN
                    'As aplicações desenvolvidas foram avaliadas durante os testes.'
                WHEN 4 THEN
                    'O desenvolvedor implementou uma solução para melhorar a recuperação.'
                ELSE
                    'Os desenvolvedores implementaram novas funcionalidades no sistema.'
            END
        ),
        -- 8. Família Recuperação
        (
            CASE g % 6
                WHEN 0 THEN 'Recuperação de informações'
                WHEN 1 THEN 'Recuperar informações'
                WHEN 2 THEN 'Recuperando informações'
                WHEN 3 THEN 'Informações recuperadas'
                WHEN 4 THEN 'Recuperação realizada'
                ELSE 'Recuperações de informações'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'A recuperação das informações foi realizada após a consulta aos documentos.'
                WHEN 1 THEN
                    'O sistema precisa recuperar informações relevantes durante a pesquisa.'
                WHEN 2 THEN
                    'A aplicação está recuperando informações armazenadas no banco de dados.'
                WHEN 3 THEN
                    'As informações recuperadas foram utilizadas durante a análise.'
                WHEN 4 THEN
                    'A recuperação realizada apresentou bons resultados durante os testes.'
                ELSE
                    'Diferentes recuperações foram avaliadas para comparar o desempenho.'
            END
        ),
        -- 9. Família Consulta
        (
            CASE g % 6
                WHEN 0 THEN 'Consulta de documentos'
                WHEN 1 THEN 'Consultar documentos'
                WHEN 2 THEN 'Consultando documentos'
                WHEN 3 THEN 'Documentos consultados'
                WHEN 4 THEN 'Consultas realizadas'
                ELSE 'Consultas de documentos'
            END,

            CASE g % 6
                WHEN 0 THEN
                    'A consulta dos documentos retornou informações relevantes para os pesquisadores.'
                WHEN 1 THEN
                    'Os usuários podem consultar documentos utilizando diferentes termos de pesquisa.'
                WHEN 2 THEN
                    'O sistema está consultando documentos armazenados no banco de dados.'
                WHEN 3 THEN
                    'Os documentos consultados apresentaram informações relacionadas ao estudo.'
                WHEN 4 THEN
                    'As consultas realizadas foram avaliadas durante os testes de desempenho.'
                ELSE
                    'Diferentes consultas de documentos foram executadas durante a pesquisa.'
            END
        ),
        -- 10. Família Documentação
        (
            CASE g % 7
                WHEN 0 THEN 'Documentação de sistemas'
                WHEN 1 THEN 'Documentar sistemas'
                WHEN 2 THEN 'Documentando sistemas'
                WHEN 3 THEN 'Sistemas documentados'
                WHEN 4 THEN 'Documento técnico'
                WHEN 5 THEN 'Documentos técnicos'
                ELSE 'Documentações de sistemas'
            END,

            CASE g % 7
                WHEN 0 THEN
                    'A documentação dos sistemas descreve as informações necessárias para compreender sua implementação.'
                WHEN 1 THEN
                    'Os desenvolvedores precisam documentar os componentes utilizados durante o desenvolvimento.'
                WHEN 2 THEN
                    'A equipe está documentando as funcionalidades implementadas na aplicação.'
                WHEN 3 THEN
                    'Os sistemas documentados apresentam informações importantes para manutenção.'
                WHEN 4 THEN
                    'O documento técnico descreveu os resultados obtidos durante os testes.'
                WHEN 5 THEN
                    'Os documentos técnicos foram utilizados pelos pesquisadores durante a análise.'
                ELSE
                    'As documentações produzidas registraram informações relevantes sobre o sistema.'
            END
        )

) AS data(title, content);