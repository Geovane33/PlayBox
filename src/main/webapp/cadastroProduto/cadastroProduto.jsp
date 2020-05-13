<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> NoteStore </title>
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
        <!--<link rel="stylesheet" href="../cssPequeno.css" media="screen and (min-width:900px)">-->
        <link rel="stylesheet" href="CadastroProduto.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
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
                        <form id="formProd"action="../notestore" method="POST">
                            <div>
                                <label></label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input value="0" type="hidden" name="id" id="id">
                            </div>
                            <div>
                                <label>Nome </label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input type="text" class="produto" minlength="2" name="nome" id="nome">
                            </div>
                            <div>
                                <label>Marca</label>
                                <input type="text" class="produto" minlength="2" name="marca" id="marca">
                            </div>
                            <div>
                                <label>Descrição</label>
                                <input type="text"class="produto" minlength="2" class="produto"name="desc" id="desc">
                            </div>
                            <div>
                                <label>Quantidade</label>
                                <input type="number"class="produto" name="qtd" id="qtd">
                            </div>
                            <div>
                                <label>Preço</label>
                                <input type="number" minlength="1" name="valor" id="valor">
                            </div>
                            <div>
                                <label>Data de entrada</label>
                                <input type="text" class="produto" name="dataEnt" id="dataEnt">
                            </div>
                            <div>
                                <input type="hidden" minlength="1"  name="idFilial" id="idFilial" placeholder="digite o id da filial" >
                            </div>
                            <div>
                                <input type="hidden"  value="Produto" type="submit" name="controller" >
                                <input type="submit" value="adicionar" id="cadastrar" name="acao">
                                <input type="reset" onclick="window.location.reload()" value="cancelar">
                            </div>
                        </form>
                    </td>

                    <td hidden class="tabela">
                        <table class="list" id="listaCadastros">
                            <thead>
                            <input type="button" value="consultar" id="consultarProd">
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
                            </thead>
                            <tbody id =tableProd >
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