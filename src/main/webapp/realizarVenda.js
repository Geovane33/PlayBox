var venda = null;

$(document).ready(function () {

    $('#verCarrinho').click(function () {

        console.log(venda);
        console.log(document);
        if (venda === null) {

            $.ajax({
                type: 'GET',
                url: 'VendaServlet',
                headers: {

                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"

                },
                success: function (result) {

                    venda = JSON.stringify(result);
                    consele.log("primeiro carregamento");
                    linhasTBV();

                }
            });

        } else {

            console.log("segundo carregamento");
            linhasTBV();

        }

    });

});

function linhasTBV() {
    for (var i = 0; i < venda.length; i++) {

        var linha = $("<tr>");
        var coluna = "";

        coluna += '<td>' + venda[i].produto + '</td>';
        coluna += '<td>' + venda[i].quantidadeNaVenda + '</td>';
        coluna += '<td>' + venda[i].dataVenda + '</td>';
        coluna += '<td>' + venda[i].valor + '</td>';
        coluna += '<td><a src="delete_forever_18dp.png" onclick="excluirItemCarrinho(' + venda[i].id + ')a>excluir<a></td>';
        linha.append(coluna);
        $("#vendasCon").append(linha);

    }
}

function excluirItemCarrinho(idVenda) {
    for (var i = 0; i < venda.length; i++) {
        if (venda[i].id === idCli) {
            venda[i] = "";
        }
    }
    $.ajax({
        type: 'GET',
        url: 'VendaServlet?id=' + idVenda,
        headers: {
            accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            alert("Item excluido com sucesso!")
        }
    });
}


