
var filiais = null;

function atribuiFilial(idFilial) {
    filiais = filiais[idFilial].nome;
    var fi = $("<h1>");
    fi.append('Filial ' + filiais);
    $("#idFilial").html(fi);
}


$(document).ready(function () {
    console.log(filiais);
    console.log(document);
    if (filiais === null) {
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
                console.log("primeiro carregamento");
                carregarFiliais();
            }});
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
            filial.append('<input type=button  value="'+ filiais[i].nome + '" onClick=enviarFilial('+i+')>');
        }
        $("#formFiliais").html(filial);
    }
}

function submitFilial(idFilial) {
    $.ajax({
        type: 'GET',
        url: 'notestore?controller=Filial&acao=consultar&filiais=' + idFilial,
        contentType: 'application/json;charset=UTF-8',
        headers: {
            Accept: "application/json;charset=UTF-8",
            "Content-Type": "application/json;charset=UTF-8"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("erro ao carregar filial");
        },
        success: function (result) {
            filiais = result;
            console.log("primeiro carregamento");
            carregarFiliais();
        }
    });
}

function enviarFilial(indice) {
    var filial = JSON.stringify(filiais[indice]);
    sessionStorage.setItem('filial', filial);
    $('form').submit();
}