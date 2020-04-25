<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Cliente</title>
    </head>
    <body>
        <h1>Formulário de Cadastro</h1>
        <form action="CadastroClienteServlet" method="POST">
            Nome:<input type="text" name="nome"/><br>
            Data de nascimento: <input type="date" name="dataNascimento"/><br>
            Sexo: <input type="text" name="sexo"/><br>
            Telefone: <input type="text" name="telefone"/><br>
            Email: <input type="text" name="email"/><br>
            Cpf: <input type="text" name="cpf"/><br>
            Cep: <input type="text" name="cep"/><br>
            Cidade: <input type="text" name="cidade"/><br>
            Uf: <input type="text" name="uf"/><br>
            Bairro: <input type="text" name="bairro"/><br>
            Numero: <input type="text" name="numero"/><br>
            <button value="salvar" name="acao" type="submit" >Enviar</button>
        </form>
        <form>
             Cpf: <input type="text" name="cpf"/><br>
            <button value="excluir" name="acao" type="submit" >Excluir</button>
        </forme>
</body>
</html>
