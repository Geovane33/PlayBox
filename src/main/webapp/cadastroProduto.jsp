<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> NoteStore </title>
    
    <link rel="stylesheet" href="cssMedio.css" media="screen and (min-width:900px)">
    <link rel="stylesheet" href="cssGrande.css" media="screen and (min-width:900px)">
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
                    <form onsubmit="event.preventDefault();cadastrar();" autocomplete="off">
                        <div>
                            <label>Nome </label><label class="validation-error hide" id="fullNameValidationError"></label>
                            <input type="text" name="nome" id="nome">
                        </div>
                        <div>
                            <label>Tipo</label>
                            <input type="text" name="tipo" id="tipo">
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
                            <input type="number" name="preco" id="preco">
                        </div>
                        <div>
                            <label>Data de entrada</label>
                            <input type="date" name="dataEnt" id="dataEnt">
                        </div>

                        <div>
                            <input type="submit" value="Cadastrar" class="btnSalvar">
                        </div>
                    </form>
                </td>
                <td class="tabela">
                    <table class="list" id="listaCadastros">
                        <thead>
                            <tr class="desc">
                                <th>Nome</th>
                                <th>Tipo</th>
                                <th>Marca</th>
                                <th>Descrição</th>
                                <th>Quantidade</th>
                                <th>Preço</th>
                                <th>Data de entrada</th>
                                <th></th>
                            </tr>
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