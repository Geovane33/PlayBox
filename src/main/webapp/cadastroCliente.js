
var cliente = null;

$(document).ready(function () {
    $('#consultarCli').click(function () {
        console.log(cliente);
        console.log(document);
        if (cliente === null) {
            $.ajax({
                type: 'GET',
                url: 'CadastroClienteServlet',
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (result) {
                    cliente = $.parseJSON(result);
                    console.log("primeiro carregamento");
                    linhasTB();
                }});
        } else {
            console.log("segundo carregamento");
            linhasTB();
        }
    }
    );
});

function linhasTB() {
    for (var i = 0; i < cliente.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + cliente[i].nome + '</td>';
        coluna += '<td>' + cliente[i].dataNascimento + '</td>';
        coluna += '<td>' + cliente[i].sexo + '</td>';
        coluna += '<td>' + cliente[i].cpf + '</td>';
        coluna += '<td>' + cliente[i].telefone + '</td>';
        coluna += '<td>' + cliente[i].email + '</td>';
        coluna += '<td>' + cliente[i].uf + '</td>';
        coluna += '<td>' + cliente[i].cep + '</td>';
        coluna += '<td>' + cliente[i].cidade + '</td>';
        coluna += '<td>' + cliente[i].bairro + '</td>';
        coluna += '<td><a src="delete_forever_18dp.png" onclick="excluirCli(' + cliente[i].id + ')"a>excluir<a></td>';
        linha.append(coluna);
        $("#clientesCon").append(linha);
    }
}

function excluirCli(idCli) {
    for (var i = 0; i < cliente.length; i++) {
        if (cliente[i].id === idCli) {
            cliente[i] = "";
        }
    }
    $.ajax({
        type: 'GET',
        url: 'CadastroClienteServlet?id=' + idCli,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            alert("Excluir com sucesso");
        }});
}

