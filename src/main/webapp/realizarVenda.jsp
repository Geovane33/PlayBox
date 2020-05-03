

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Realizar venda</title>

        <link rel="stylesheet" href="cssMedio.css" media="screen and (min-width:900px)">
        <link rel="stylesheet" href="cssGrande.css" media="screen and (min-width:900px)">
    </head>
    <body>
        <h1>Realizar venda</h1>
        <section>
            <table>
                <tr>
                    <td class="venda">
                        <form action="VendarServlet" autocomplete="off" method="POST">
                            <div>
                                <label>Cliente </label><label class="validation-error hide" id="fullNameValidationError"></label>
                                <input type="text" name="nome" id="nome">
                            </div>
                            <div>
                                <label>Produto </label>
                                <input type="text" name="produto" id="produto">
                            </div>
                            <div>
                                <label>Quantidade </label>
                                <input type="number" name="quantidade" id="quantidade">
                            </div>
                            <br>
                            <div>
                                <label>Valor unidade: R$ ###,## </label>
                            </div>
                            <br>
                            <div>
                                <label>Valor total: R$ #.###,##</label>
                            </div>
                            <br>
                            <div>
                                <hr>
                                <br>
                                <label>Total carrinho: R$ #.###,##</label>
                                <br>
                                <br>
                            </div>
                            <div>
                                <input type="button" value="Adicionar ao carrinho">
                                <input type="reset" value="Apagar">
                                <input type="button" value="Comprar">
                            </div>
                        </form>
                    </td>

                    <td class="tabela">
                        <table class="carrinho" id="carrinhoCompras">
                            <thead>
                                <tr class="desc">
                                    <th>Produto</th>
                                    <th>Quantidade</th>
                                    <th>Data da compra</th>
                                    <th>Valor</th>         
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

    </body>
</html>
