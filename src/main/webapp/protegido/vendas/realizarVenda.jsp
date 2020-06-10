<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendas</title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
        <link rel="stylesheet" href="../style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
        <script src="realizarVenda.js"></script>
        <link rel="stylesheet" href="vendas.css" >
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
    </head>
    <body>
        <div class="corpo" hidden>
            <h2>Vendas</h2>
            <section>
                <table>
                    <tr>
                        <td class="cadastro">
                            <form action="CadastroProdutoServlet" autocomplete="off" method="POST">
                                <div>
                                    <label id="cliSelecionado">CLIENTE</label><label class="validation-error hide" id="fullNameValidationError"></label>
                                    <!--<input type="text" placeholder="consulte um cliente"value="" id="teste">-->
                                    <select id="listarClientes">
                                        <option value="n">---</option>
                                    </select>

                                </div>
                                <div>
                                    <label>PRODUTO </label>
                                    <select id="listarProdutos" onchange="getValorProd()">

                                    </select>
                                </div>
                                <div>
                                    <label>Quantidade </label>
                                    <input type="number" value="" onchange="getTotalVenda()" name="quantidade" min="0" max="0" id="quantidade">
                                </div>
                                <br>
                                <div>
                                    <label id="valorUnitario">Valor Unidade: R$ 0</label>
                                </div>
                                <br>
                                <div>
                                    <label id="valorTotal">Valor Total: R$ 0</label>
                                </div>
                                <br>
                                <div>
                                    <hr>
                                    <br>
                                    <label id="totalCarrinho">Total Carrinho: R$ 0</label>
                                    <br>
                                    <br>
                                </div>
                                <div>
                                    <header>
                                        <a class="btn" type="button" onclick="adicionarProduto()" value="Adicionar ao carrinho" id="adcCarrinho">Adicionar ao carrinho</a>
                                        <a class="btn" type="button" onclick="gerarVenda()" value="Finalizar" id="finalizar">Finalizar</a>
                                        <a class="btn" type="reset" onclick="window.location.reload()" value="cancelar" id="cancelar">Cancelar</a>
                                    </header>
                                </div>
                            </form>
                        </td>

                        <td class="tabela">
                            <table class="list" id="listaCadastros">
                                <thead>
                                    <tr class="desc">
                                        <th>Produto</th>
                                        <th>Valor Unitário</th>
                                        <th>Quantidade</th>
                                        <th>Total</th>         
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody  id="tableCarrinho">

                                </tbody>
                            </table>
                        </td>
                    </tr>

                </table>

            </section>
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
                        <!--                    <li>
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
                                            </li>    -->
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>