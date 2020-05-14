
var relatorio = {};
var carregou = true;
var filial = {};
var totalFilial = 0;
var controleTabela = -1;
var tabela2;
$(document).ready(() => {
    init();
});

function init() {
    getFilial();
    obterRelatorio();
//    tabelaDinamica2();
}

function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        $('#tabelaRelatorio').hide();
        alert("Erro ao obter filial");
        window.location.href = '../';
    } else {
        $('#tabelaRelatorio').show();
    }
}

function carregaTabela() {
    for (var i = 0; i < relatorio.length; i++) {
        insereRelatorio(relatorio[i]);
        calculaTotalVenda(relatorio[i]);
    }
    tabelaDinamica();
    $("#totalFilial").text("Total Venda Filial: R$ " + totalFilial);
}

function calculaTotalVenda(relatorio) {
    totalFilial += relatorio.total;
}

//function removeLinha() {
//    i = document.getElementById('tabelaLinhasProd').getElementsByTagName('tr').length;
//    console.log(i);
//    for (; i > 0; i--) {
//        document.getElementById('tabelaLinhasProd').getElementsByTagName('tr')[0].remove();
//    }
//}

function carregaTabela2() {

    for (var i = 0; i < relatorio.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[i].filial.nome + '</td>';
        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
        coluna += '<td>' + relatorio[i].total + '</td>';
        coluna += '<td>' + relatorio[i].dataVenda + '</td>';
        coluna += '<td><input class="btnProduto" value=" ' + relatorio[i].produtos.length + '" type="button" onclick="getProdutos(' + i + ')"></td>';
        linha.append(coluna);
        $("#tabelaLinhas").append(linha);
    }
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


    console.log(i);
    
      for (var j = 0; j < relatorio[i].produtos.length; j++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].nome + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].marca + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].quantidade + '</td>';
        coluna += '<td>' + relatorio[i].produtos[j].valor + '</td>';
        linha.append(coluna);
        $("#tabelaLinhasProd").append(linha);
    }
        tabelaDinamica2();
    
    
//    if (controleTabela !== i) {
//        controleTabela = i;
    // removeLinha();
   

}
//    $('.divTabela').show();

//    tabela2.row.add({
//        "cliente": "Tiger Nixon",
//        "nome": "System Architect",
//        "marca": "$3,120",
//        "qunatidade": "2011/04/25",
//        "preço": "Edinburgh"
//    }).draw();

//}


function tabelaDinamica2() {
//    if (document.getElementById('tabelaLinhasProd').getElementsByTagName('tr').length > 0) {
//        tabela2.destroy();
////    }
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
                carregaTabela2();
            }
        }});
}

//// Insere os dados na Tabela
//function insereRelatorio(relatorio) {
//    var table = document.getElementById("tabela").getElementsByTagName('tbody')[0];
//    var newRow = table.insertRow(table.length);
//    cell1 = newRow.insertCell(0); // Nome
//    cell1.innerHTML = relatorio.filial.nome;
//    cell2 = newRow.insertCell(1); // nome do cliente
//    cell2.innerHTML = relatorio.cliente.nome;
//    cell3 = newRow.insertCell(2); //total por venda
//    cell3.innerHTML = relatorio.total;
//    cell4 = newRow.insertCell(3); //data da venda
//    cell4.innerHTML = relatorio.dataVenda;
//}
