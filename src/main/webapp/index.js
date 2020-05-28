$(document).ready(function () {
    init();
});

var filiais = [];


function init() {
    obterFiliais();
}

function atribuiFilial(idFilial) {
    filiais = filiais[idFilial].nome;
    var fi = $("<h1>");
    fi.append('Filial ' + filiais);
    $("#idFilial").html(fi);
}

function obterFiliais() {
    if (filiais.length === 0) {
        $.ajax({
            type: 'GET',
            url: 'notestore?controller=Filial&acao=consultar',
            contentType: 'application/json;charset=UTF-8',
            headers: {
                Accept: "application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            },
            success: function (result) {
                filiais = result;
                carregarFiliais();
            }});
    }
}


function carregarFiliais() {
    $("#formFiliais").html("<h3>Carregando filiais</h3>");
    var filial = $("<h3>");
    if (filiais.length < 1) {
        $("#formFiliais").html("<h3> Nenhuma filial cadastrada</h3>");
    } else {

        for (var i = 0; i < filiais.length; i++) {
            console.log(filiais[i]);
            filial.append('<a class="btn" onclick="enviarFilial(' + i + ')">' + filiais[i].nome + '</a><br>');
        }
        filial.append('<a class="btn" onclick="relatorioGeral()">Relatório Geral</a><br>');
        $("#formFiliais").html(filial);
    }
}

function enviarFilial(indice) {
    var filial = JSON.stringify(filiais[indice]);
    sessionStorage.setItem('filial', filial);
    $('form').submit();
}

function relatorioGeral() {
    var filial = {};
    filial.id = 0;
    filial.nome = 'Relatório Geral';
    filial.estado = 'Relatório Geral';
    sessionStorage.setItem('filial', JSON.stringify(filial));
    window.location.href = 'relatorios/relatorio.jsp';
}