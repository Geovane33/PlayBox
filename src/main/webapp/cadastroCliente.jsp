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
            sex0o: <input type="text" name="sexo"/><br>
            Telefone: <input type="text" name="telefone"/><br>
            Email: <input type="text" name="email"/><br>
            <button type="submit" >Enviar</button>
        </form>
    </body>
</html>
