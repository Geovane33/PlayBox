
var filiais = null;
var filial;
var nomeFilial;

$(document).ready(function () {
    recebeFilial();
    if (filial.id === 0) {
        $("#form").hide();
    }
}
);
function carregarFiliais() {
    $("#formFiliais").html("<h3>Carregando filiais</h3>");
    var filial = $("<h3>");
    if (filiais.length < 1) {
        $("#formFiliais").html("<h3> Nenhuma filial cadastrada</h3>");
    } else {
        for (var i = 0; i < filiais.length; i++) {
            console.log(filiais[i]);
            filial.append('<button name="filiais" value="' + filiais[i].id + '">Filial ' + filiais[i].nome + '</button>');

        }
        $("#formFiliais").html(filial);
    }
}

function recebeFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    $("#idFilial").append(filial.nome);
}
