<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta content="text/html; charset=UTF-8" >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src='filiais.js'></script>
    </head>
    <body>
        <%
            String filial = request.getParameter("filial");
        %>

        <h1 id='idFilial'>${filial}</h1>
        <form action="cadastroCliente=idFiliaRecebida">
            <a href="cadastroCliente/cadastroCliente.jsp">Clientes</a>
            <a href="cadastroProduto/cadastroProduto.jsp">Produtos</a>
            <a href="realizarVenda.jsp">Vendas</a>

        </form>
    </body>
</html>