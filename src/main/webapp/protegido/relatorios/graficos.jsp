<%-- 
    Document   : graficos
    Created on : 09/06/2020, 00:44:13
    Author     : Geovane
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
        <link rel="stylesheet" href="../style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script> 
        <script src="graficos.js"></script>
        <title>GRÁFICOS - NOTESTORE</title>
    </head>
    <body style="position: relative; left: 56px">
        <canvas class="line-chart"></canvas>
        
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
                        <a href="../cadastroCliente/cadastroCliente.jsp">
                            <div class="menu-item">        
                                <span class="icon clientes"></span>
                                <span class="description">CLIENTES</span>        
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="../cadastroProduto/cadastroProduto.jsp">
                            <div class="menu-item">        
                                <span class="icon produtos"></span>
                                <span class="description">PRODUTOS</span>        
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="../relatorios/relatorio.jsp">
                            <div class="menu-item">        
                                <span class="icon relatorios"></span>
                                <span class="description">RELATÓRIOS</span>        
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
