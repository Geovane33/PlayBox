<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta content="text/html; charset=UTF-8" >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="filiais/filiais.js"></script>
    </head>
    <body>

        <h1 id='idFilial'></h1>
        <form action="cadastroCliente">
            <a href="cadastroCliente/cadastroCliente.jsp">Clientes</a>
            <a href="cadastroProduto/cadastroProduto.jsp">Produtos</a>
            <a href="vendas/realizarVenda.jsp">Realizar Venda</a>
              <a href="relatorios/relatorioProduto.jsp">Relatorios</a>
        </form>
    </body>
</html>