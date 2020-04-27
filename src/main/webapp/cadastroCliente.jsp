<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Cliente</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="cadastroCliente.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Formulário de Cadastro</h1>
        <form action="CadastroClienteServlet" method="POST">
            Nome:<input id="nome_cli" type="text" name="nome" /><br>
            Data de nascimento:<input type="date" name="dataNascimento"/><br>
            <label for="sexo">Sexo:</label>
            <select name="sexo">
                <option value="Masculino">Masculino</option>
                <option value="Feminino">Feminino</option>
            </select><br>
            Telefone: <input type="text" name="telefone" /><br>
            Email: <input type="email" name="email" /><br>
            Cpf: <input type="text" name="cpf" /><br>
            Cep: <input type="text" name="cep" /><br>
            Cidade: <input type="text" name="cidade" /><br>
            Uf: <input type="text" name="uf" /><br>
            Bairro: <input type="text" name="bairro" /><br>
            Numero: <input type="text" name="numero" /><br>
            <button value="salvar" name="acao" type="submit">Enviar</button>
        </form>
        <form action="CadastroClienteServlet" method="POST">
            Cpf: <input type="text" name="cpf" /><br>
            <button value="excluir" name="acao" type="submit">Excluir</button>
        </forme>
        <a type="button" style="cursor:pointer"id="consultarCli"> consultar </a>

        <table  id="clientesCon" align="center" border="1">
            <tr>
                <th>NOME</th>
                <th>NASCIMENTO</th>
                <th>SEXO</th>
                <th>CPF</th>
                <th>TELEFONE</th>
                <th>E-MAIL</th>
                <th>CEP</th>
                <th>CIDADE</th>
                <th>BAIRRO</th>
                <th>ALTERAR</th>
                <th>EXCLUIR</th>
            </tr>
        </table>
        <a id="consulCli"></a>
</body>

</html>