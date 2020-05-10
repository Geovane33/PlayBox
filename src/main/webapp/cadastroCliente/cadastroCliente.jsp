<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> NoteStore </title>

        <link rel="stylesheet" href="../style/cssPequeno.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="CadastroCliente.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script src="cadastroCliente.js"></script>
    </head>

    <section>
        <table>
            <tr>
                <td class="cadastro">
                    <!-- Compos para preencher -->

                    <form action="../notestore?controller=Cliente" method="POST">
                        <div>
                            <label></label><label class="validation-error hide" id="fullNameValidationError"></label>
                            <input  value="0" type="text" id="id" name="id">
                        </div>
                        <div>
                            <!--type="hidden"-->
                            <label>idFilial</label><label class="validation-error hide" id="fullNameValidationError"></label>
                            <input type="text" name="idFilial" id="idFilial" placeholder="id da filial">
                        </div>
                        <div>
                            <label>Nome </label><label class="validation-error hide" id="fullNameValidationError"></label>
                            <input type="text" name="nome" id="nome" placeholder="seu nome">
                        </div>
                        <div>
                            <label>CPF</label>
                            <input type="text"  id="CPF" name="CPF" placeholder="seu CPF">
                        </div>
                        <div>
                            <label>Nascimento</label>
                            <input type="text" id="nascimento" name="nascimento"/>
                        </div>
                        <div>
                            <label>Sexo</label>
                            <input type="text" name="sexo" id="sexo" placeholder="informe seu sexo">
                        </div>
                        <div>
                            <label>Telefone</label>
                            <input type="text" name="telefone" id="telefone" placeholder="seu telefone">
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
                            <input type="text" name="CEP" id="CEP" placeholder="seu CEP">
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
                            <input type="text" name="numero" id="numero" placeholder="numero da sua casa" >
                        </div>
                        <div>
                            <button value="adicionar" id="cadastrar" name="acao" type="submit" >cadastrar</button>
                        </div>
                    </form>
                </td>
            <div class="inputConsult">
                <p id="radio">Selecione o tipo de consulta:</p>
                <input type="radio" name="consulta" value="nome" onclick="buttonRadio(this.value)"CHECKED>
                <label id="consulTipo" for="nome">Nome</label>
                <input type="radio"   name="consulta" value="CPF" onclick="buttonRadio(this.value)">
                <label id="consulTipo" for="CPF">CPF</label>
                <input type="text" id="campo" placeholder="nome">
                <input type="button" value="consultar" id="consultarCli">
            </div>  
            <input type="button" onclick="removeLinha()" value="reset"> 
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
                        <!--                        <tr id="tableClientes">
                                                <tr>-->
                    </thead>

                    <tbody  id="tableClientes">
                        <!--                    <div id="tableClientes">
                                                
                                            </div>-->
                    </tbody>
                </table>
            </td>
            </tr>
        </table>
    </section>

    <footer>
    </footer>
</html>