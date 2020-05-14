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
                            <input class="NOME" type="text" name="nome" id="nome" minlength="2" placeholder="nome">
                        </div>
                        <div>
                            <label for="CPF">CPF*</label>
                            <input class="CPF"ype="text"  id="CPF" name="CPF" placeholder="CPF">
                        </div>
                        <div>
                            <label for="nascimento">Nascimento*</label>
                            <input class="NASCIMENTO" type="text" id="nascimento" name="nascimento"/>
                        </div>
                        <div>
                            <label for="sexo">Sexo*</label>
                            <input class="SEXO"type="text" name="sexo" id="sexo" placeholder="sexo">
                            <option></option>
                        </div>
                        <div>
                            <label for="telefone">Telefone</label>
                            <input type="text" name="telefone" id="telefone" placeholder="telefone">
                        </div>
                        <div>
                            <label for="email">E-mail*</label>
                            <input class="EMAIL" type="email" name="email" id="email" placeholder="e-mail">
                        </div>
                        <div>
                            <label class="UF" for="uf">UF*</label>
                            <select class="UF" id="uf" name="uf">
                                <option value="">---</option>
                                <option value="AC">AC</option>
                                <option value="AL">AL</option>
                                <option value="AP">AP</option>
                                <option value="AM">AM</option>
                                <option value="BA">BA</option>
                                <option value="CE">CE</option>
                                <option value="DF">DF</option>
                                <option value="ES">ES</option>
                                <option value="GO">GO</option>
                                <option value="MA">MA</option>
                                <option value="MT">MT</option>
                                <option value="MS">MS</option>
                                <option value="MG">MG</option>
                                <option value="PR">PR</option>
                                <option value="PB">PB</option>
                                <option value="PA">PA</option>
                                <option value="PE">PE</option>
                                <option value="PI">PI</option>
                                <option value="RJ">RJ</option>
                                <option value="RN">RN</option>
                                <option value="RS">RS</option>
                                <option value="RO">RO</option>
                                <option value="RR">RR</option>
                                <option value="SC">SC</option>
                                <option value="SE">SE</option>
                                <option value="SP">SP</option>
                                <option value="TO">TO</option>
                            </select>
                        </div>
                        <div>
                            <label for="CEP">CEP</label>
                            <input type="text" name="CEP" id="CEP" placeholder="seu CEP">
                        </div>
                        <div>
                            <label for="cidade">Cidade*</label>
                            <input class="CIDADE" type="text" name="cidade" id="cidade" placeholder="cidade"><br>
                        </div>
                        <div>
                            <label for="bairro">Bairro</label>
                            <input type="text" name="bairro" id="bairro" placeholder="bairro" >
                        </div>
                        <div>
                            <label for="numero">Numero</label>
                            <input type="text" pattern="[0-9]+$" required name="numero" id="numero" placeholder="numero" >

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