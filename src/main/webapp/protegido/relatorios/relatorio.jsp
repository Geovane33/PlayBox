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
        <link rel="stylesheet" href="https://datatables.net/media/css/site-examples.css?_=6239e0117a45e8466919cf6525f8d1f2" type="text/css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" type="text/css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.jqueryui.min.css" type="text/css">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
        <link rel="stylesheet" href="../style.css">
        <link rel="stylesheet" href="relatorio.css" type="text/css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.21/datatables.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
        <script src="relatorio.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorios</title>
    </head>
    <body style="position: relative; left: 59px">
        <div hidden class="corpo">
            <table id="tabela"  class="display" style="width:100%" border="1" width="1" cellspacing="2">
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
            <div class="divTabela">
            </div>
            <div class="labels">
                <label id="totalFilial">Valor Bruto Das Vendas: R$ 0</label><br>
                <label id="qtdVendas">Quantidade De Vendas Realizadas: 0</label><br>
            </div>
        </div>
        <!--partial:index.partial.html--> 
        <div id="navMenu" class="nav-menu collapsed">
            <div class="menu-header">
                <div class="menu-title">Menu</div>
                <a id="toggleMenu" class="menu-toggle" href="#">
                    <span class="icon collapse"></span>    
                </a>
            </div>
            <div class="menu-items">
                <ul id="listNav" class="list">
                    <li>
                        <a href="../index.html">
                            <div class="menu-item">        
                                <span class="icon home"></span>
                                <span class="description">INICIO</span>        
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="../../logout">
                            <div class="menu-item">        
                                <span class="icon logout"></span>
                                <span class="description">LOGOUT</span>        
                            </div>
                        </a>
                    </li>    
                </ul>
            </div>
        </div>
    </body>
</html>
