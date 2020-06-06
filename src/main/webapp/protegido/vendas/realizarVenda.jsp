<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html hidden>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realizar venda</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="realizarVenda.js"></script>
        <link rel="stylesheet" href="vendas.css" >
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
        <!--<link rel="stylesheet" href="../style/cssPequeno.css" media="screen and (min-width:900px)">-->
    </head>
    <body>
        <h1>Realizar Venda</h1>
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

    </body>
</html>