<%-- 
    Document   : relatorioJ
    Created on : 13/05/2020, 08:35:24
    Author     : Geovane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" type="text/css">
        <link rel="stylesheet" href="raltorio.css" type="text/css">
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script src="relatorioProduto.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table id="tabela" border="1" width="1" cellspacing="2">
            <thead>
                <tr>
                    <th>Filial</th>
                    <th>Cliente</th>
                    <th>Valor</th>
                     <th>Data</th>
                </tr>
            </thead>
            <!---->
            <tbody id="tabela">
<!--                <tr>
                    <td>teste1</td>
                    <td>teste1</td>
                    <td>teste1</td>
                </tr>
                <tr>
                    <td>teste2</td>
                    <td>teste2</td>
                    <td>teste2</td>
                </tr>
                <tr>
                    <td>teste3</td>
                    <td>teste3</td>
                    <td>teste3</td>
                </tr>-->
            </tbody>
        </table>
        <label id="totalFilial">Total Venda Filial: </label>
    </body>
</html>
