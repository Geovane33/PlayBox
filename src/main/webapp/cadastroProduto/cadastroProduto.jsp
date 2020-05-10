<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> NoteStore </title>
        <link rel="stylesheet" href="../cssMedio.css" media="screen and (min-width:900px)">
        <!--<link rel="stylesheet" href="../cssPequeno.css" media="screen and (min-width:900px)">-->
        <link rel="stylesheet" href="CadastroProduto.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="cadastroProduto.js"></script>
    </head>
    <body>
        <header>
            <h1>Cadastro de produto</h1>
        </header>

        <section>
            <table>
                <tr>
                    <td class="cadastro">
                        <!-- Compos para preencher -->
                        <form action="../CadastroProdutoServlet" method="POST">
                            <div>
                                <label>Nome </label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input type="text" name="nome" id="nome">
                            </div>
                            <div>
                                <label>Marca</label>
                                <input type="text" name="marca" id="marca">
                            </div>
                            <div>
                                <label>Descrição</label>
                                <input type="text" name="desc" id="desc">
                            </div>
                            <div>
                                <label>Quantidade</label>
                                <input type="number" name="qtd" id="qtd">
                            </div>
                            <div>
                                <label>Preço</label>
                                <input type="number" name="valor" id="valor">
                            </div>
                            <div>
                                <label>Data de entrada</label>
                                <input type="text" name="dataEnt" id="dataEnt">
                            </div>
                            <div>
                                <label>Filial</label>
                                <input type="number" name="idFilial" id="idFilial" placeholder="digite o id da filial" >
                            </div>
                            <div>
                                <button value="Cadastrar" id="cadastrar" name="acao" >cadastrar</button>
                            </div>
                        </form>
                        <a id="consultarProd">consultar</a>
                    </td>
                    <td class="tabela">
                        <table class="list" id="listaCadastros">
                            <thead>
                                <tr class="desc">
                                    <th>Nome</th>
                                    <th>Marca</th>
                                    <th>Descrição</th>
                                    <th>Quantidade</th>
                                    <th>Preço</th>
                                    <th>Data de entrada</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <tr id="tableProd">
                                <tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </table>
        </section>

        <footer>
        </footer>
        <!--        <script src="script.js"></script>-->
    </body>
</html>