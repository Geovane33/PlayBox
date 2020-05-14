<%-- 
    Document   : relatorio
    Created on : 13/05/2020, 08:35:24
    Author     : Geovane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="relatorio.css" type="text/css">
        <link rel="stylesheet" href="https://datatables.net/media/css/site-examples.css?_=6239e0117a45e8466919cf6525f8d1f2" type="text/css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" type="text/css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.jqueryui.min.css" type="text/css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>
        <script src="relatorio.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorios</title>
    </head>
    <body>
        <table id="tabela" class="display" style="width:100%" border="1" width="1" cellspacing="2">
            <thead>
                <tr>
                    <th>Filial</th>
                    <th>Cliente</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>produtos</th>
                </tr>
            </thead>
            <tbody id="tabelaLinhas">
            </tbody>
        </table>
        <label id="totalFilial">Total Venda Filial: R$ 0</label>
        <div class="divTabela">
<!--            <table id="tabela2" class="display" style="width:100%" border="1" width="1" cellspacing="2">
                <thead>
                    <tr>
                        <th>Cliente</th>
                        <th>Nome</th>
                        <th>Marca</th>
                        <th>Quantidade</th>
                        <th>Preço</th>
                    </tr>
                </thead>
                <tbody id="tabelaLinhasProd">
                </tbody>
            </table>-->
        </div>
    </body>
</html>
