
var produto = null;

$(document).ready(function () {
    $('#dataEnt').mask('00/00/0000',
            {'translation':
                        {0: {pattern: /[0-7*]/}, optional: false}, placeholder: '__/__/__'});


    $('#consultarProd').click(function () {
        console.log(produto);
        console.log(document);
        if (produto === null) {
            $.ajax({
                type: 'GET',
                url: '../CadastroProdutoServlet',
                headers: {
                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                },
                success: function (result) {
                    produto = result;
                    console.log("primeiro carregamento");
                    carregaTabela();
                }});
        } else {
            console.log("segundo carregamento");
            carregaTabela();
        }
    }
    );
});

function carregaTabela() {
    for (var i = 0; i < produto.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + produto[i].nome + '</td>';
        coluna += '<td>' + produto[i].marca + '</td>';
        coluna += '<td>' + produto[i].descricao + '</td>';
        coluna += '<td>' + produto[i].quantidade + '</td>';
        coluna += '<td>' + produto[i].valor + '</td>';
        coluna += '<td>' + dataAtualFormatada(produto[i].dataDeEntrada) + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirProd(' + produto[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarProd(' + i + ')"></td>';
        linha.append(coluna);
        $("#listaCadastros").append(linha);
    }
}

function validar(){
    
}

function dataAtualFormatada(data){
             console.log(data);
    var novaData = new Date(data),
        dia  = (novaData.getDate()).toString(),
        diaF = (dia.length === 1) ? '0'+dia : dia,
        mes  = (novaData.getMonth()+1).toString(), //+1 pois no getMonth Janeiro começa com zero.
        mesF = (mes.length === 1) ? '0'+mes : mes,
        anoF = novaData.getFullYear();
    return diaF+"/"+mesF+"/"+anoF;
}

function editarProd(indice) {
    document.getElementById("nome").value = produto[indice].nome;
    document.getElementById("marca").value = produto[indice].marca;
    document.getElementById("desc").value = produto[indice].descricao;
    document.getElementById("qtd").value = produto[indice].quantidade;
    document.getElementById("valor").value = produto[indice].valor;
    document.getElementById("dataEnt").value = dataAtualFormatada(produto[indice].dataDeEntrada);
    document.getElementById("idFilial").value = produto[indice].idFilial;
    document.getElementById("cadastrar").innerHTML = "atualizar";
    document.getElementById("cadastrar").value = "atualizar";
}

function excluirProd(idProd) {
    for (var i = 0; i < produto.length; i++) {
        if (produto[i].id === idProd) {
            produto[i] = null;
        }
    }
    $.ajax({
        type: 'GET',
        url: '../CadastroProdutoServlet?id=' + idProd,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            alert("Excluir com sucesso");
        }});
}

