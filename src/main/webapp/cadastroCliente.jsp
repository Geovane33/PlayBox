<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> NoteStore </title>

        <link rel="stylesheet" href="cssMedio.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="cssGrande.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="CadastroCliente.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="cadastroCliente.js"></script>
        <script> function gettoday() { var today = new Date(); var dd = today.getDate(); var mm = today.getMonth() + 1; //January is 0! var yyyy = today.getFullYear(); if (dd < 10) { dd = '0' + dd; } if (mm < 10) { mm = '0' + mm; } var today = dd + '/' + mm + '/' + yyyy; return today; } document.getElementById('test').innerHTML= gettoday(); </script>
    </head>
    <body>
        <header>
            <h1>Cadastro de cliente</h1>
        </header>

        <section>
            <table>
                <tr>
                    <td class="cadastro">
                        <!-- Compos para preencher -->
                        <form action="CadastroClienteServlet" autocomplete="off" method="POST">
                            <div>
                                <label>idFilial</label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input type="text" name="idFilial" id="idFilial" placeholder="id da filial">
                            </div>
                            <div>
                                <label>Nome </label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input type="text" name="nome" id="nome" placeholder="seu nome">
                            </div>
                            <div>
                                <label>CPF</label>
                                <input type="number" name="cpf" id="cpf" placeholder="seu CPF">
                            </div>
                            <div>
                                <label>Nascimento</label>
                                <input type="date" name="nascimento" id="nascimento" value="<fmt:formatDate value='${dataNascimento}' pattern='yyyy/MM/dd'/>"/>
                            </div>
                            <div>
                                <label>Sexo</label>
                                <input type="text" name="sexo" id="sexo" placeholder="informe seu sexo">
                            </div>
                            <div>
                                <label>Telefone</label>
                                <input type="number" name="telefone" id="telefone" placeholder="seu telefone">
                            </div>
                            <div>
                                <label>E-mail</label>
                                <input type="text" name="email" id="email" placeholder="seu e-mail">
                            </div>

                            <div>
                                <label>UF</label>
                                <input type="text" name="uf" id="uf" placeholder="UF">
                            </div>
                            <div>
                                <label>CEP</label>
                                <input type="number" name="cep" id="cep" placeholder="seu CEP">
                            </div>
                            <div>
                                <label>Cidade</label>
                                <input type="text" name="cidade" id="cidade" placeholder="sua cidade">
                            </div>
                            <div>
                                <label>Bairro</label>
                                <input type="text" name="bairro" id="bairro" placeholder="seu bairro" >
                            </div>
                            <div>
                                <label>Numero</label>
                                <input type="number" name="numero" id="numero" placeholder="numero da sua casa" >
                            </div>
                            <div>
                                <button value="salvar" id="cadastrar" name="acao"type="submit" >cadastrar</button>
                            </div>
                        </form>
                        <a id="consultarCli">consultar</a>
                    </td>
                    <td class="tabela">
                        <table class="list" id="listaCadastros">
                            <thead>
                                <tr class="desc">
                                    <th>NOME</th>
                                    <th>CPF</th>
                                    <th>NASCIMENTO</th>
                                    <th>SEXO</th>
                                    <th>TELEFONE</th>
                                    <th>E-MAIL</th>
                                    <th>UF</th>
                                    <th>CEP</th>
                                    <th>CIDADE</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <tr id="tableCli">
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
        <script src="script.js"></script>
    </body>
</html>