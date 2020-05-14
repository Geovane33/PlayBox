SELECT 
    COUNT(v.id_venda) AS 'QTD Vendas Filias'
FROM
    filial AS f
        INNER JOIN
    venda AS v ON f.id_filial = v.id_filial
        AND f.id_filial = '2';
    
    -- consulta quantidade de vendas realizadas em determinada loja sem info TB Filial
SELECT 
    COUNT(*) AS 'QTD Vendas Uma Filial'
FROM
    venda
WHERE
    venda.id_filial = '2';
    
-- consulta quantidade de vendas realizadas em todas as filiais
SELECT 
    COUNT(*) 'QTD Venda Todas Filiais'
FROM
    venda;
    
-- total de vendas(valor bruto) de todas as lojas
SELECT 
    SUM(v.total_venda) AS 'Venda Valor Bruto'
FROM
    venda v;
    
-- total de vendas(valor bruto) de determinada loja e/ou cliente -- alternativa de pesquisa por nome de loja para realizar levantamento de dados
SELECT 
    SUM(v.total_venda) AS 'Venda Valor Bruto Filial'
FROM
    venda v
WHERE
    v.id_filial = '2';-- and v.id_cliente='1';
    
    -- produtos vendido em determinada venda, tabela preparada para VendaDAO        
SELECT 
    venda_produto.quantidade_produto as quantidade_produto_na_venda,
    produto.ID_produto id_produto,
    produto.nome_produto nome_produto,
    produto.quantidade_produto quantidade_produto,
    produto.valor_produto valor_produto
FROM
    venda_produto
        INNER JOIN
    produto ON produto.ID_produto = venda_produto.id_produto
WHERE
    id_venda = 1; 
    
-- --------------------- PRODUTOS ----------------------------
SELECT 
    SUM(vp.quantidade_produto) AS 'QTD Total 
    Produtos Vendidos'
FROM
    venda_produto vp
WHERE
    vp.id_venda = '2';
    
-- quantos produtos vendidos em uma determinada venda de uma determinada filial -- quantos produtos vendidos em uma determinada venda
SELECT 
    SUM(vp.quantidade_produto) AS 'QTD Produtos Filial e Venda'
FROM
    venda v
        INNER JOIN
    venda_produto vp ON v.id_venda = vp.id_venda
        AND v.id_venda = '2'
        AND v.id_filial = '2';
    
-- quantos produtos vendidos em todas vendas
SELECT 
    SUM(venda_produto.quantidade_produto) AS 'QTD Produtos Vendidos'
FROM
    venda_produto;
    
-- quais produtos foram vendidos em determinada venda sem info TB produtos
SELECT 
    v.id_venda, vp.id_produto
FROM
    venda v
        INNER JOIN
    venda_produto vp ON v.id_venda = vp.id_venda
        AND v.id_venda = '2';
    
        
-- quais produtos foram vendidos em determinada venda com info TB produto
SELECT 
    v.id_venda, vp.id_produto, p.nome_produto, p.valor_produto
FROM
    venda v
        INNER JOIN
    venda_produto vp ON v.id_venda = vp.id_venda
        INNER JOIN
    produto p ON vp.id_produto = p.id_produto
        AND v.id_venda = '2';
        
-- quantos tipos de produtos determinada loja tem
SELECT 
    COUNT(*) AS 'Tipos Produtos'
FROM
    produto p
WHERE
    p.id_filial = '3';
    
-- quantidade total de produtos que determinada loja tem
SELECT 
    SUM(produto.quantidade_produto) AS 'QTD Prod Filial'
FROM
    produto
WHERE
    produto.id_filial = '3';

-- ---------------- CLIENTES -------------------
-- consulta o que determinado cliente comprou em determinada filial sem info da TB venda_produtos
SELECT 
    *
FROM
    filial AS f
        INNER JOIN
    venda AS v ON f.id_filial = v.id_filial
WHERE
    f.id_filial = '2' AND v.id_cliente = '2';

-- consulta o que determinado cliente comprou em determinada filial com info TB venda_produtos
SELECT 
    *
FROM
    filial AS f
        INNER JOIN
    venda AS v ON f.id_filial = v.id_filial
        INNER JOIN
    venda_produto AS vp ON v.id_venda = vp.id_venda
WHERE
    f.id_filial = '2' AND v.id_cliente = '2';
    
-- quantos clientes tem cadastrados em todas as lojas
SELECT 
    COUNT(*)
FROM
    cliente;
    
-- quantos clientes comprou em determinada filial.  Diferente do valor de venda se 2 cli fez 5 compras são 10 vendas e 2 clientes
SELECT 
    COUNT(DISTINCT v.id_cliente) as 'QTD Clientes Realizou Compras na Filial '
FROM
    cliente c
        INNER JOIN
    venda v ON c.id_cliente = v.id_cliente
WHERE
    v.id_filial = '2';
    
-- quantos clientes comprou em determinada filial, sem inner join
SELECT 
    COUNT(DISTINCT v.id_cliente) as 'QTD Clientes Realizou Compras na Filial '
FROM
    venda v,
    filial f
WHERE
    f.id_filial = '2';