
var relatorio = {};
var carregou = true;
var filial = {};
var totalFilial;
$(document).ready(() => {
    init();
    
//    function () {
//
//        $("#output").pivotUI(
//                [
//                    {color: "blue", shape: "circle"},
//                    {color: "red", shape: "triangle"}
//                ],
//                {
//                    rows: ["color"],
//                    cols: ["shape"]
//                }
//        );
//    }
//    ;
});

function init() {
    getFilial();
    obterRelatorio();
    setTimeout(()=> tabela(), 100);


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
//        TotalFilial();
    }
   
}

function totalFilial(relatorio){
    
}


function tabela() {
    $('#tabela').DataTable();
}
function obterRelatorio() {
    $('#obterRelatorio').click(() => {
//        consultarClientes();
    });
//    consulta = $("#campo").val();
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
                console.log(relatorio);
                carregaTabela();
            }
        }});
}

// Insere os dados na Tabela
function insereRelatorio(relatorio) {
    var table = document.getElementById("tabela").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);

    cell1 = newRow.insertCell(0); // Nome
    cell1.innerHTML = relatorio.filial.nome;
    cell2 = newRow.insertCell(1); // nome do cliente
    cell2.innerHTML = relatorio.cliente.nome;
    cell3 = newRow.insertCell(2); //total por venda
    cell3.innerHTML = relatorio.total;
    cell4 = newRow.insertCell(3); //data da venda
    cell4.innerHTML = dataAtualFormatada(relatorio.dataVenda);
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