<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset='UTF-8'">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> NoteStore </title>

        <link rel="stylesheet" href="../style/cssPequeno.css" media="screen and (min-width:100px)">
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="CadastroCliente.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="http://jqueryvalidation.org/files/dist/jquery.validate.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script> 
        <script src="cadastroCliente.js"></script>
    </head>

    <section id ="cliente">
        <table>
            <tr>
                <td class="cadastro">
                    <!-- Compos para preencher -->

                    <form id="formCad"action="../notestore" method="POST">
                        <div>
                            <input  type="hidden" value="0" type="text" id="id" name="id">
                        </div>
                        <div>
                            <input type="hidden"  value="0" name="idFilial" id="idFilial" placeholder="id da filial">
                        </div>
                        <div>
                            <label for="nome">Nome*</label>
                            <input class="cliente" type="text" name="nome" id="nome" minlength="2" placeholder="nome">
                        </div>
                        <div>
                            <label for="CPF">CPF*</label>
                            <input class="cliente"type="text"  id="CPF" name="CPF" placeholder="CPF">
                        </div>
                        <div>
                            <label for="nascimento">Nascimento*</label>
                            <input class="cliente" type="text" id="nascimento" name="nascimento"/>
                        </div>
                        <div>
                            <label for="sexo">Sexo*</label>
                            <input class="cliente"type="text" name="sexo" id="sexo" placeholder="sexo">
                        </div>
                        <div>
                            <label for="telefone">Telefone</label>
                            <input type="text" name="telefone" id="telefone" placeholder="telefone">
                        </div>
                        <div>
                            <label for="email">E-mail*</label>
                            <input class="cliente" type="email" name="email" id="email" placeholder="e-mail">
                        </div>

                        <div>
                            <label for="uf">UF*</label>
                            <input class="cliente" type="text" minlength="2" maxlength="2" name="uf" id="uf" placeholder="UF">
                        </div>
                        <div>
                            <label for="CEP">CEP</label>
                            <input type="text" name="CEP" id="CEP" placeholder="seu CEP">
                        </div>
                        <div>
                            <label for="cidade">Cidade*</label>
                            <input class="cliente" type="text" name="cidade" id="cidade" placeholder="cidade"><br>
                        </div>
                        <div>
                            <label for="bairro">Bairro</label>
                            <input type="text" name="bairro" id="bairro" placeholder="bairro" >
                        </div>
                        <div>
                            <label for="numero">Numero</label>
                            <input type="text" name="numero" id="numero" placeholder="numero da casa" >

                            <input type="hidden" value="Cliente" name="controller">
                        </div>
                        <div>
                            <input value="adicionar" type="submit" id="cadastrar" name="acao">
                            <input type="reset" onclick="window.location.reload()" value="cancelar">
                        </div>
                    </form>
                </td>
                <td hidden class="tabela">  
                    <table class="list" id="listaCadastros">
                        <label id="labelCPF" >CPF</label>
                        <label id="labelNome" >Nome</label>
                        <input type="radio" id="radioCPF" name="consulta" value="nome" onclick="buttonRadio(this.value)"CHECKED>
                        <input type="radio" id="radioNome" name="consulta" value="CPF" onclick="buttonRadio(this.value)">
                        <input type="search" id="campo" placeholder="nome"><br>
                        <input type="button" id="consultarCli"value="consultar" id="">
                        <thead>
                            <tr class="desc">
                                <th>NOME</th>
                                <th>CPF</th>
                                <th>NASCIMENTO</th>
                                <th>E-MAIL</th>
                                <th>CIDADE</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody  id="tableClientes">
                        </tbody>
                    </table>
                </td>
            </tr>
        </table>
    </section>
    <footer>
    </footer>
</html>