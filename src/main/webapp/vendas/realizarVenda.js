/**
 * chamar o init ao carregar pagina
 */
$(document).ready(function () {
    init();
});
var venda = {total: 0};
var produtoCarrinho = [];
var cliente = [];

function init() {
    listarClientes();
    listarProduto();
    getFilial();
}

/**
 * chamada no mesmo momento que a pagina carregar
 */
function getFilial() {
    venda.filial = JSON.parse(sessionStorage.getItem('filial'));
    if (venda.filial === null) {
        alert("voce não tem acesso a essa filial");
        window.location.href = 'https://www.google.com.br/';
    }
}

/**
 * Listar clientes adicionada cada um em uma tag option
 */
function listarClientes() {
    $.ajax({
        type: 'GET',
        url: '../notestore?controller=Cliente&acao=consultar',
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, beforeSend: function (xhr) {
            xhr.setRequestHeader('X-Consulta', "");
            xhr.setRequestHeader('X-ConsultaTipo', "nome");
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert("erro ao carregar lista de clientes");
        },
        success: function (result) {
            cliente = result;
            carregarListCli();
        }}
    );

}
function carregarListCli() {
    for (i = 0; i < cliente.length; i++) {
        cliente[i].dataNascimento = dataAtualFormatada(cliente[i].dataNascimento);
        var lista = "";
        lista += '<option value="' + i + '">' + cliente[i].nome + '</option>';
        $("#listarClientes").append(lista);
    }
}


/**
 * resquest para receber uma lsita de produtos
 */
function listarProduto() {
    getFilial();
    $.ajax({
        type: 'GET',
        url: '../CadastroProdutoServlet?idFilial=' + venda.filial.id,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("erro ao carregar lista de produtos");
        },
        success: function (result) {
            produto = result;
            carregaListaProd();
        }});
}

/**
 * Listar produtos adicionando cada um em uma tag option
 */
function carregaListaProd() {
    var lista = '<option value="n">---</option>';
    $("#listarProdutos").append(lista);
    for (i = 0; i < produto.length; i++) {
        lista = "";
        lista += '<option value="' + i + '">' + produto[i].nome + '</option>';
        $("#listarProdutos").append(lista);
    }
}

/**
 * obter(setar na tag/view) o valor da unidade do produto e a quantidade em estoque para impedir venda em maior quantidade
 * também é feito a chamada do metodo getTotal para setar na view o valor total referente a quantidade de produtos
 * 
 */
function getValorProd() {
    var indice = $("#listarProdutos").val();
    if (indice === 'n') {
        $("#valorUnitario").text("Valor unidade: R$ 0");
        document.getElementById("quantidade").value = 0;
        document.getElementById("quantidade").max = 0;
    } else {
        $("#valorUnitario").text("Valor unidade: R$ " + produto[indice].valor);
        document.getElementById("quantidade").value = 0;
        document.getElementById("quantidade").max = produto[indice].quantidade;
    }
    getTotalVenda();
}
/**
 * obter o valor total referente a quantiade de um produto comprado
 */
function getTotalVenda() {
    var indice = $("#listarProdutos").val();
    var quantidade = $("#quantidade").val();
    var total = 0.0;
    for (i = 0; i < quantidade; i++) {
        total += produto[indice].valor;
    }
    $("#valorTotal").text("Valor Total: R$ " + total);
}

/**
 * adicionar produtos ao carrinho de compras, atualizar a tabela e quantidade de produtos
 */
function adicionarProduto() {

    var quantidade = $("#quantidade").val();
    var indiceProd = $("#listarProdutos").val();
    var indiceClien = $("#listarClientes").val();
    var form = "Selecione";

    indiceClien === 'n' ? form += "\nCliente" : "";

    indiceProd === 'n' ? form += "\nProduto" : "";

    quantidade < 1 ? form += "\nQuantidade" : "";

    if (form !== "Selecione") {
        alert(form);
    } else {

        var totalCar = 0.0;
        for (i = 0; i < quantidade; i++) {
            totalCar += produto[indiceProd].valor;
        }

        this.produto[indiceProd].quantidadeNaVenda = quantidade;
        produtoCarrinho.push({
            produto: this.produto[indiceProd],
            VlrTotalCar: totalCar
        });
        atualizaVenda(this.cliente[indiceClien], totalCar);

        atualizaQtdProd(indiceProd, quantidade);
        tabelaCarrinho();
        carregaListaProd();
    }
}

/*
 * Atualizar venda
 * @param {inteiro} idCliente
 * @param {number} totalCar
 * @returns {number}
 */

function atualizaVenda(cli, totalCar) {
    venda = {
        cliente: cli,
        total: somaTotalVenda(totalCar)
    };
}

function somaTotalVenda(totalCar) {
    venda.total += totalCar;
    $("#totalCarrinho").text("Total Carrinho: R$ " + venda.total);
    return venda.total;
}

/**
 * atualizar a quantidade do estoque de produtos, para depois realizar a venda
 * @param {inteiro} indice
 * @param {inteiro} qtd
 */
function atualizaQtdProd(indice, qtd) {
    produto[indice].quantidade -= qtd;
    if (produto[indice].quantidade === 0) {
        produto.splice(indice, 1);
    }
    atualizarListaProd();
    limpaForm();
}

/**
 * Removendo elementos da combobox
 * @param {inteiro} selecionado
 */
function removerProdutoDaLista(selecionado) {
    var optionProd = document.getElementById("listarProdutos");
    optionProd.remove(selecionado);
}

/**
 * Removendo todos os elementos
 */
function atualizarListaProd() {
    var optionProd = document.getElementById("listarProdutos");
    while (optionProd.length) {
        optionProd.remove(0);
    }
}
;
/**
 * limpar o fomulario de venda
 */
function limpaForm() {
    document.getElementById("listarProdutos").selectedIndex = 0;
    $("#listarClientes").hide();
    var indiceClien = $("#listarClientes").val();
    $("#cliSelecionado").text("CLIENTE: " + cliente[indiceClien].nome);
    ;
    $("#valorUnitario").text("Valor Unidade: R$ 0");
    $("#valorTotal").text("Valor Total: R$ 0");
    document.getElementById("quantidade").value = 0;
    document.getElementById("quantidade").max = 0;
}

/**
 * metodo manter a tabela "atualizada" remove todas as linhas a cada vez que atualiza a linhasTBV
 */
function reiniciarTabela() {
    i = document.querySelectorAll('tr').length - 2;
    for (; i > 0; i--) {
        document.getElementById('tableCarrinho').getElementsByTagName('tr')[0].remove();
    }
}

/**
 * Atualiza o carrinho de compras
 */
function tabelaCarrinho() {
    reiniciarTabela();
    for (var i = 0; i < produtoCarrinho.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + produtoCarrinho[i].produto.nome + '</td>';
        coluna += '<td>' + produtoCarrinho[i].produto.valor + '</td>';
        coluna += '<td>' + produtoCarrinho[i].produto.quantidadeNaVenda + '</td>';
        coluna += '<td>' + produtoCarrinho[i].VlrTotalCar + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onclick="excluirItem(' + i + ')"></td>';
        linha.append(coluna);
        $("#listaCadastros").append(linha);
    }
}

/**
 * exclui itens do carrinho
 * @param {inteiro} i 
 */
function excluirItem(i) {
    atualizaVenda(venda.cliente, -produtoCarrinho[i].VlrTotalCar);
    produtoCarrinho.splice(i, 1);
    tabelaCarrinho();
}

function excluirItemCarrinho(idVenda) {

    for (var i = 0; i < venda.length; i++) {
        if (venda[i].id === idCli) {
            venda[i] = "";
        }
    }
    $.ajax({
        type: 'GET',
        url: 'VendaServlet?id=' + idVenda,
        headers: {
            accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            alert("Item excluido com sucesso!")
        }
    });
}

function getProdVenda() {
    venda.produtos = [];
    for (i = 0; i < produtoCarrinho.length; i++) {
        venda.produtos.push(produtoCarrinho[i].produto);
    }
}

function gerarVenda() {
    getProdVenda();
    getFilial();
    $.ajax({
        type: 'POST',
        url: '../notestore?controller=Venda&acao=adicionar',
        headers: {
            accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, beforeSend: function (xhr) {
            xhr.setRequestHeader('X-Venda', JSON.stringify(venda));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Erro ao finalizar venda!");
        },
        success: function (result) {
            if (result === '200') {
                alert("Venda finalizada!");
                window.location.reload();
            } else {
                alert("Erro ao finalizar venda");
            }


        }
    });
}
function dataAtualFormatada(data) {
    var novaData = new Date(data),
            dia = (novaData.getDate()).toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (novaData.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = novaData.getFullYear();
    return dia + "" + mes + "" + anoF;
}