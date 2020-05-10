
var cliente = null;
var dataNascimento = null;
var consultaTipo = 'nome';

$(document).ready(function () {
    $('#nascimento').mask("00/00/0000", {placeholder: "dd/mm/aaaa"});
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
        consulta = $("#campo").val();
        $.ajax({
            type: 'GET',
            url: '../notestore?controller=Cliente&acao=consultar',

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
    }
    );
});

function buttonRadio(tipo) {
    this.consultaTipo = tipo;
    if (consultaTipo === 'CPF') {
        $('#campo').mask('ZZZ.ZZZ.ZZZ-ZZ', {
            translation: {
                'Z': {
                    pattern: /[0-9]/, optional: false
                }
            },
            placeholder: "___.___.___-__"
        });
    } else {
        $('#campo').unmask();
        $("#campo").attr("placeholder", "nome");
        $("#campo").attr("pattern", "[A-Za-z]{4,20}");
    }

    console.log(this.consultaTipo);
}

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
}

function carregaTabela() {
    removeLinha();
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
        url: '../notestore?controller=Cliente&acao=excluir&id=' +idCli,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        error: function (jqXHR, textStatus, errorThrown) {
             alert("Erro ao excluir cliente");
        },
        success: function (result) {
            console.log(result);
            alert(result);
        }});
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

