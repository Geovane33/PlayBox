
var relatorio = {};
var carregou = true;
var filial = {};
var totalFilial = 0;
var controleTabela = -1;
var tabela2;
var idCLientes = [];
$(document).ready(() => {
    init();
});

function init() {
    getFilial();
    obterRelatorio();
}

function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        $('#tabelaRelatorio').hide();
         $('body').hide();
        alert("Erro ao obter filial");
        window.location.href = '../';
    } else {
        $('#tabelaRelatorio').show();
        $('body').show();
    }
}

function calculaTotalVenda(relatorio) {
    totalFilial += relatorio.total;
}

function carregaTabela() {

    for (var i = 0; i < relatorio.length; i++) {
        calculaTotalVenda(relatorio[i]);
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[i].filial.nome + '</td>';
        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
        coluna += '<td>R$ ' + relatorio[i].total + '</td>';
        coluna += '<td>' + relatorio[i].dataVenda + '</td>';
        coluna += '<td><input class="btnProduto" value=" ' + relatorio[i].produtos.length + '" type="button" onclick="getProdutos(' + i + ')"></td>';
        linha.append(coluna);
        $("#tabelaLinhas").append(linha);
    }
    $("#totalFilial").text("Valor Bruto Das Vendas: R$ " + totalFilial);
    $("#qtdVendas").text("Quantidade De Vendas Realizadas " + relatorio.length);
    tabelaDinamica();
}

function criarColunas() {
    $("#tabela2_wrapper").remove();
    var table = $("<table />", {
        id: 'tabela2'
    });
    $(".divTabela").append(table);

    var thead = $("<thead />", {
        id: 'thead'
    });

    $("#tabela2").append(thead);

    var coluna = ('<tr><th>Cliente</th>'
            + '<th>Nome</th>'
            + '<th>Marca</th>'
            + '<th>Quantidade</th>'
            + '<th>Preço</th></tr>');
    $("#thead").append(coluna);

    var tbody = $("<tbody />", {
        id: 'tabelaLinhasProd'
    });
    $("#tabela2").append(tbody);
}

function getProdutos(i) {
    criarColunas();
    for (var j = 0; j < relatorio[i].produtos.length; j++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].nome + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].marca + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].quantidade + '</td>';
        coluna += '<td>R$ ' + relatorio[i].produtos[j].valor + '</td>';
        linha.append(coluna);
        $("#tabelaLinhasProd").append(linha);
    }
    tabelaDinamica2();

}

function tabelaDinamica2() {
    tabela2 = $('#tabela2').DataTable({
        "language": {
            "url": "../jsons/produtos.json"
        }
    });
}

function tabelaDinamica() {
    $('#tabela').DataTable({
        "language": {
            "url": "../jsons/vendas.json"
        }
    });
}

function obterRelatorio() {
    $('#obterRelatorio').click(() => {
    });
    $.ajax({
        type: 'GET',
        url: '../notestore?controller=Relatorio&acao=consultar&idFilial=' + filial.id,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert("erro na solicitação");
        },
        success: function (result) {
            if (result === "" && carregou) {
                carregou = false;
                alert("Nenhuma venda encontrada para gerar relatorio");
            } else {
                carregou = false;
                relatorio = result;
                carregaTabela();
            }
        }});
}
