
var filiais = null;

function atribuiFilial(idFilial){
filiais=filiais[idFilial].nome;
var fi = $("<h1>");
fi.append('Filia '+filiais);
$("#idFilial").html(fi);
}


$(document).ready(function () {
    console.log(filiais);
    console.log(document);
    if (filiais === null) {
        $.ajax({
            type: 'POST',
            url: 'ControllerFiliais',
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            success: function (result) {
                filiais = $.parseJSON(result);
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
            filial.append('<button name="filiais" value="' + filiais[i].id + '">Filial ' + filiais[i].nome + '</button>');

        }
        $("#formFiliais").html(filial);
    }
}
