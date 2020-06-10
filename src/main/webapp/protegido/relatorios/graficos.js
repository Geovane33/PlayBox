window.onload = function () {
    init();
};
var relatorio = [];
var nomesFiliais = [];
var qtdVendas = [];
var expan = false;

function getNomeFiliais(){
        for (var i = 0; i < relatorio.length; i++) {
//            qtdVendas.push(relatorio[i].total);
//        coluna += '<td>' +  + '</td>';
//        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
//        coluna += '<td>R$ ' + relatorio[i].total + '</td>';
//        coluna += '<td>' + relatorio[i].dataVenda + '</td>';
//        coluna += '<td><input class="btnProduto" value=" ' + relatorio[i].produtos.length + '" type="button" onclick="getProdutos(' + i + ')"></td>';
//        linha.append(coluna);
//        $("#tabelaLinhas").append(linha);
    
    nomesFiliais.push(relatorio[i].filial.nome);
    }
     carregarGrafico();
}



function init() {
    obterRelatorio();
    expand();
}
//top 5 filiais
function carregarGrafico() {
    var ctx = document.getElementsByClassName("line-chart");
    //type, data,options
    var chartGraph = new Chart(ctx, {
        type: 'bar',
        data: {
//            labels: ["Jan", "Fev", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"],
                labels: nomesFiliais,
            datasets: [{
                    label: "TAXA DE CLIQUES - 2017",
                   // data: [5, 10, 5, 14, 20, 15, 6, 14, 8, 12, 15, 5, 10],
                   data: qtdVendas,
                    borderWidth: 3,
                    borderColor: "rgba(77,166,253,0.85)",
                    backgroudColor: 'transparent'
                }]

        }
    });
}

function obterRelatorio() {
    $('#obterRelatorio').click(() => {
    });
    $.ajax({
        type: 'GET',
        url: '../../notestore?controller=Relatorio&acao=consultar&idFilial=' + 0,
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
                getNomeFiliais();
//                carregaTabela();
            }
        }});
}

function expand() {
    $("#toggleMenu").on("click", function () {
        var menu = $("#navMenu");

        menu.toggleClass("collapsed");
        menu.toggleClass("expanded");
        if (expan) {
            $("body").css("left", "59px");
            expan = false;
        } else {
            $("body").css("left", "278px");
            expan = true;
        }

    });
}
