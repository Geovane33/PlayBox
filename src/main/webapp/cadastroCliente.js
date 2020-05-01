
var cliente = null;
var dataNascimento = null;

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
                    cliente = result;
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

//function linhasTB() {
//    $("#tableCli").text();
//    for (var i = 0; i < cliente.length; i++) {
//        var linha = $("<tr>");
//        var coluna = $("<td>");
//        coluna.append(cliente[i].nome);
//        coluna.append(cliente[i].dataNascimento);
//        coluna.append(cliente[i].sexo);
//        coluna.append(cliente[i].cpf);
//        coluna.append(cliente[i].telefone);
//        coluna.append(cliente[i].email);
//        coluna.append(cliente[i].uf);
//        coluna.append(cliente[i].cep);
//        coluna.append(cliente[i].cidade);
//        coluna.append(cliente[i].bairro);
//        coluna.append('<a src="delete_forever_18dp.png" onclick="excluirCli(' + cliente[i].id + ')"a>excluir<a>');
//        linha.append(coluna);
//        $("#clientesCon").append(linha);
//    }
//}

function linhasTB() {

    console.log("Linhas tb" + cliente.length);
    for (var i = 0; i < cliente.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + cliente[i].nome + '</td>';
        coluna += '<td>' + cliente[i].cpf + '</td>';
        coluna += '<td>' + cliente[i].dataNascimento + '</td>';
        coluna += '<td>' + cliente[i].sexo + '</td>';
        coluna += '<td>' + cliente[i].telefone + '</td>';
        coluna += '<td>' + cliente[i].email + '</td>';
        coluna += '<td>' + cliente[i].uf + '</td>';
        coluna += '<td>' + cliente[i].cep + '</td>';
        coluna += '<td>' + cliente[i].cidade + '</td>';
        coluna += '<td><img class="imgDel" src="icons/baseline_delete_forever_black_18dp.png" onClick="excluirCli(' + cliente[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="icons/outline_edit_black_18dp.png" onClick="editarCli(' + i + ')"></td>';
        linha.append(coluna);
        $("#listaCadastros").append(linha);
    }
}

function editarCli(indice) {
    document.getElementById("nome").value = cliente[indice].nome;
    document.getElementById("cpf").value = cliente[indice].cpf;
//    document.getElementById("dataNascimento").value = new Date(cliente[indice].dataNascimento);
//    dataNascimento = cliente[indice].dataNascimento;
    document.getElementById("sexo").value = cliente[indice].sexo;
    document.getElementById("telefone").value = cliente[indice].telefone;
    document.getElementById("email").value = cliente[indice].email;
    document.getElementById("uf").value = cliente[indice].uf;
    document.getElementById("cidade").value = cliente[indice].cidade;
    document.getElementById("cep").value = cliente[indice].cep;
    document.getElementById("bairro").value = cliente[indice].bairro;
    document.getElementById("numero").value = cliente[indice].numero;
    document.getElementById("cadastrar").innerHTML = "atualizar";
}

function excluirCli(idCli) {
    for (var i = 0; i < cliente.length; i++) {
        if (cliente[i].id === idCli) {
            cliente[i] = null;
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

