<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta content="text/html; charset=UTF-8" >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="filiais/filiais.js"></script>
        <link rel="stylesheet" href="style/cssMedio.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="style/cssPequeno.css" media="screen and (min-width:100px)">
        <link rel="stylesheet" href="style/index.css" media="screen and (min-width:400px)">
        <link rel="stylesheet" href="filiais/filiais.css">
    </head>
    <body>

        <h1 id='idFilial'></h1>
        <form id="form" action="cadastroCliente">
            <a value="Clientes" href="cadastroCliente/cadastroCliente.jsp"><input type="button" value="Clientes"></a><br>
            <a type="button" value="Produtos" href="cadastroProduto/cadastroProduto.jsp"><input type="button" value="Produtos"></a><br>
            <a type="button" value="Vendas" href="vendas/realizarVenda.jsp"><input type="button" value="Vendas"></a><br>
        </form>
         <a type="button" value="Relatórios" href="relatorios/relatorio.jsp"><input type="button" value="Relatorios"><a/>
    </body>
</html>