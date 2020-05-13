
var produto = [];
var filial = {};

function init() {
    getFilial();
    form();
    mask();
    consultarProd();
    gerarVenda();

}



function mask() {
    $('#dataEnt').mask('00/00/0000',
            {'translation':
                        {0: {pattern: /[0-9*]/}, optional: false}, placeholder: '__/__/__'});
}

$(document).ready(function () {
    init();
});

function consultarProd() {
    $('#consultarProd').click(function () {
        $.ajax({
            type: 'GET',
            url: '../notestore?controller=Produto&acao=consultar&idFilial=' + filial.id,
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            success: function (result) {
                if (result === '') {
                    alert("Nenhum produto cadastrado na filial: "+ filial.nome);
                } else {
                    produto = result;
                    carregaTabela();
                }
            }});
    }
    );
}

/**
 * metodo manter a tabela "atualizada" remove todas as linhas a cada vez que atualiza a linhasTBV
 */
function removeLinha() {
    i = document.querySelectorAll("tr").length - 2;
    for (; i > 0; i--) {
        document.getElementById('tableProd').getElementsByTagName('tr')[0].remove();
    }
}

function carregaTabela() {
    removeLinha();
    for (var i = 0; i < produto.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + produto[i].nome + '</td>';
        coluna += '<td>' + produto[i].marca + '</td>';
        coluna += '<td>' + produto[i].descricao + '</td>';
        coluna += '<td>' + produto[i].quantidade + '</td>';
        coluna += '<td>' + produto[i].valor + '</td>';
        coluna += '<td>' + dataAtualFormatada(produto[i].dataDeEntrada) + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirProd(' + i + ', ' + produto[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarProd(' + i + ')"></td>';
        linha.append(coluna);
        $("#listaCadastros").append(linha);
    }
}

function dataAtualFormatada(data) {
    var novaData = new Date(data),
            dia = (novaData.getDate()).toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (novaData.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = novaData.getFullYear();
    return diaF + "/" + mesF + "/" + anoF;
}

function editarProd(indice) {
    document.getElementById("id").value = produto[indice].id;
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
/**
 * Atualiza excluir produto
 * @param {number} i
 * @param {number} idProd
 */
function excluirProd(i, idProd) {
    if(confirm("Deseja excluir?")){
    produto.splice(i, 1);
    carregaTabela();

    $.ajax({
        type: 'GET',
        url: '../notestore?controller=Produto&acao=excluir&id=' + idProd,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Erro ao excluir Produto");
        },
        success: function (result) {
            alert("Produto excluido com sucesso");
        }});}else{
            alert("cancelado");
        }
}

function form() {
    validarForm();
    $('form').ajaxForm({
        onsubmit: function (event) {
        },
        success: function (result, textStatus, jqXHR) {
            if (result !== '') {
                alert(result);
                window.location.reload();
            } else {
                alert('erro no servidor ao cadastrar produto');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Erro ao solicitar');
        }
    });

}

function validarForm() {
    //Valida o formulário
    $("#formProd").validate();
    //apelido necessário para o cRequired com nova mensagem
    $.validator.addMethod("cRequired", $.validator.methods.required,
            "Campo obrigatorio");
    // apelido cMinlength
    $.validator.addMethod("cMinlength", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    // combina os dois, aplicando as regras nos campos que contenham a classe chamada "cliente"
    $.validator.addClassRules("produto", {cRequired: true, cMinlength: 2});
}
function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        alert("Erro ao obter filial");
        window.location.href = '../';
    } else {
        $('.tabela').show();
    }
    document.getElementById("idFilial").value = filial.id;
}