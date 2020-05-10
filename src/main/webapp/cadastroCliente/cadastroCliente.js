
var cliente = null;
var dataNascimento = null;
var consultaTipo = 'nome';

$(document).ready(function () {
    $('#nascimento').mask("00/00/0000", {placeholder: "__/__/____"});
    $('#telefone').mask('(ZZ) Z-ZZZZ-ZZZZ', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: false
            }
        },
        placeholder: "(__) _ - ____ - ____"
    });
    $('#CEP').mask('ZZZZZ-ZZZ', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: false
            }
        },
        placeholder: "_____-___"
    });
    $('#CPF').mask('ZZZ.ZZZ.ZZZ-ZZ', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: false
            }
        },
        placeholder: "___.___.___-__"
    });

    $('#consultarCli').click(function () {
//        if (cliente === null) {
        consulta = $("#campo").val();
        $.ajax({
            type: 'GET',
            url: '../CadastroClienteServlet?acao=consultar',

            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            }, beforeSend: function (xhr) {
                xhr.setRequestHeader('X-Consulta', consulta);
                xhr.setRequestHeader('X-ConsultaTipo', consultaTipo);

            },
            success: function (result) {
                cliente = result;
                console.log("primeiro carregamento");
                carregaTabela();
            }});
//        } else {
//            console.log("segundo carregamento");
//            carregaTabela();
//        }
    }
    );
});

function buttonRadio(tipo) {
    this.consultaTipo = tipo;
    if (consultaTipo === 'CPF') {
        $('#campo').mask('ZZZ.ZZZ.ZZZ-ZZ', {
            translation: {
                'Z': {
                    pattern: /[0-9]/, optional: true
                }
            },
            placeholder: "___.___.___-__"
        });
    } else {
        $('#campo').unmask();
        $("#campo").attr("placeholder", "nome");
    }

    console.log(this.consultaTipo);
}

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

function criarColuna() {
    var coluna = $("tr");
    var nome = "";
    nome += '<th>NOME</th>';
    nome += '<th>CPF</th>';
    nome += '<th>NASCIMENTO</th>';
    nome += '<th>SEXO</th>';
    nome += '<th>TELEFONE</th>';
    nome += '<th>E-MAIL</th>';
    nome += '<th>UF</th>';
    nome += '<th>CEP</th>';
    nome += '<th>CIDADE</th>';
    nome += '<th>AÇÃO</th>';
    coluna.append(nome);
    $("#tableClientes").append(coluna);
}

function removeLinha() {

    i = document.querySelectorAll('tr').length - 2;
    console.log(i);
    for (; i > 0; i--) {
        document.getElementById('tableClientes').getElementsByTagName('tr')[0].remove();
    }

//  document.getElementById('tableClientes').getElementsByTagName('tr')[0].remove();
}

function carregaTabela() {
    removeLinha();
//     $("#tableClientes").children().remove();
//    criarColuna();
    //$("#tableClientes").children().remove();//("<th>NOME</th><th>CPF</th><th>NASCIMENTO</th><th>SEXO</th><th>TELEFONE</th><th>E-MAIL</th><th>UF</th><th>CEP</th><th>CIDADE</th><th></th><th></th>");
//    document.getElementById("desc").innerHTML = "<th>NOME</th><th>CPF</th><th>NASCIMENTO</th><th>SEXO</th><th>TELEFONE</th><th>E-MAIL</th><th>UF</th><th>CEP</th><th>CIDADE</th><th></th><th></th>";
//     var div = $("<div id=tableClientes>");
//            div.append("tabela clientes");
//       $("#tbodyClientes").append(div);
    for (var i = 0; i < cliente.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + cliente[i].nome + '</td>';
        coluna += '<td>' + cliente[i].cpf + '</td>';
        coluna += '<td>' + dataAtualFormatada(cliente[i].dataNascimento) + '</td>';
        coluna += '<td>' + cliente[i].sexo + '</td>';
        coluna += '<td>' + cliente[i].telefone + '</td>';
        coluna += '<td>' + cliente[i].email + '</td>';
        coluna += '<td>' + cliente[i].uf + '</td>';
        coluna += '<td>' + cliente[i].cep + '</td>';
        coluna += '<td>' + cliente[i].cidade + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirCliente(this ,' + cliente[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarCliente(' + i + ')"></td>';
        linha.append(coluna);
        $("#tableClientes").append(linha);
    }

}

function editarCliente(indice) {
    document.getElementById("id").value = cliente[indice].id;
    document.getElementById("idFilial").value = cliente[indice].idFilial;
    document.getElementById("nome").value = cliente[indice].nome;
    document.getElementById("CPF").value = cliente[indice].cpf;
    document.getElementById("nascimento").value = dataAtualFormatada(cliente[indice].dataNascimento);
    document.getElementById("sexo").value = cliente[indice].sexo;
    document.getElementById("telefone").value = cliente[indice].telefone;
    document.getElementById("email").value = cliente[indice].email;
    document.getElementById("uf").value = cliente[indice].uf;
    document.getElementById("cidade").value = cliente[indice].cidade;
    document.getElementById("CEP").value = cliente[indice].cep;
    document.getElementById("bairro").value = cliente[indice].bairro;
    document.getElementById("numero").value = cliente[indice].numero;
    document.getElementById("cadastrar").innerHTML = "atualizar";
    document.getElementById("cadastrar").value = "atualizar";
}


function excluirCliente(td, idCli) {
    linha = td.parentElement.parentElement;
    document.getElementById("tableClientes").deleteRow(linha.rowIndex-1);
    console.log(td);
    console.log(idCli);
    $.ajax({
        type: 'GET',
        url: '../CadastroClienteServlet?acao=excluir&id=' + idCli,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            console.log(result);
            alert("Excluido com sucesso");
        }});
}

function consultaTB() {
    var template = document.querySelector("#tbClientes");
    var corpo_tabela = document.querySelector("#tableClientes");
    lista_td = template.content.querySelectorAll("td");
    for (var i = 0; i < cliente.length; i++) {
        lista_td[0].textContent = cliente[i].nome;
        lista_td[1].textContent = cliente[i].cpf;
        lista_td[2].textContent = cliente[i].nascimento;
        lista_td[3].textContent = cliente[i].sexo;
        lista_td[4].textContent = cliente[i].telefone;
        lista_td[5].textContent = cliente[i].email;
        lista_td[6].textContent = cliente[i].uf;
        lista_td[7].textContent = cliente[i].cep;
        lista_td[8].textContent = cliente[i].cidade;
        lista_td[9].textContent = cliente[i].numero;
        // template.appendChild('<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirCli('+ cliente[i].id +')"></td>');
        //lista_td.create() ('<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarCli(' + i + ')"></td>');
    }
    var nova_linha = document.importNode(template.content, true);
    corpo_tabela.appendChild(nova_linha);
}

function dataAtualFormatada(data) {
    var novaData = new Date(data),
            dia = (novaData.getDate()).toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (novaData.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = novaData.getFullYear();
    return dia + "/" + mes + "/" + anoF;
}

