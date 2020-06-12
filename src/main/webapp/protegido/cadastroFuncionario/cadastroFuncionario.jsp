
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
    <head>
        <meta charset='UTF-8'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> FUNCIONARIOS - NOTESTORE </title>
        <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>
        <link rel="stylesheet" href="../style.css">
        <link rel="stylesheet" href="cadastroFuncionario.css">
        <link rel="stylesheet" href="../style/cssPequeno.css" media="screen and (min-width:100px)">
        <link rel="stylesheet" href="../style/cssMedio.css" media="screen and (min-width:900px)">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script src="../plugins/jqueryValidate.js"></script>
        <script src="../plugins/jqueryForm.js"></script> 
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
        <script src="cadastroFuncionario.js"></script>
    </head>
    <body class="body" >
        <div class="corpo">
            <h2>Funcionários</h2>
            <hr>

            <section id ="cliente">
                <table>
                    <tr>
                        <td class="cadastro">
                            <!-- Campos para preencher -->
                            <form id="formCad"action="../../notestore" method="POST">
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
                                    <label class="funcao" for="funcao">Funcao*</label>
                                    <select class="funcao" id="funcao" name="funcao">
                                        <option value="">---</option>
                                        <option value="gerente">Gerente</option>
                                        <option value="vendedor">Vendedor</option>
                                        <option value="estoquista">Estoquista</option>
                                    </select>
                                </div>
                                <div>
                                    <label for="telefone">Salario*</label>
                                    <input type="text" name="salario" id="salario" placeholder="telefone">
                                </div>
                                <div>
                                    <label for="email">Usuario*</label>
                                    <input class="usuario" type="text" name="usuario" id="usuario" placeholder="usuario">
                                </div>
                                <div>
                                    <label for="senha">Senha*</label>
                                    <input class="senha" type="password" name="senha" id="senha" placeholder="senha">
                                </div>
                                <header>
                                    <div>
                                        <input type="hidden" value="Funcionario" name="controller">
                                        <input class="btnInput" value="Adicionar" type="submit" id="cadastrar" name="acao">
                                        <a class="btn" type="reset" onclick="window.location.reload()" value="cancelar" id="cancelar">Cancelar</a>
                                    </div>
                                </header>
                            </form>
                        </td>
                        <td hidden class="tabela">  
                            <table class="list" id="listaCadastros">
                                <input type="search" id="campo" placeholder="nome"><br>
                                <a class="btn" type="button" id="consultarCli"value="consultar" id="consultarCli">Consultar</a>
                                <thead>
                                    <tr class="desc">
                                        <th>NOME</th>
                                        <th>FUNCAO</th>
                                        <th>SALARIO</th>
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
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
