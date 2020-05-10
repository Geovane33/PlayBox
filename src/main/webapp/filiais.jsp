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
            <a href="cadastroCliente.jsp">Cadastro de Cliente</a>
            <a href="cadastroProduto.jsp">Cadastro de Produto</a>
            <a href="vendas\realizarVenda.jsp">Realizar Venda</a>

        </form>
    </body>
</html>