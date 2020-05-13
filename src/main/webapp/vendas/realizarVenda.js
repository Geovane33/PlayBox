/**
 * chamar o init ao carregar pagina
 */
$(document).ready(function () {
    init();
});
var venda = {total: 0};
var produtoCarrinho = [];
var cliente = [];
var produto = [];
function init() {
    getFilial();
    listarClientes();
    listarProduto();
    limpaForm();
}

/**
 * chamada no mesmo momento que a pagina carregar
 */
function getFilial() {
    venda.filial = JSON.parse(sessionStorage.getItem('filial'));
    if (venda.filial === null) {
        alert("Erro ao obter filial");
        window.location.href = '../';
    } else {
        $('html').show();
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
            if (result === undefined) {
                alert("Nenhum cliente cadastrado: "+ venda.filial.nome);
            } else {
                cliente = result;
                carregarListCli();
            }
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
        url: '../notestore?controller=Produto&acao=consultar&idFilial=' + venda.filial.id,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, success: function (result) {
            console.log(result);
            if (result === '') {
              alert("Nenhum produto cadastrado na filial: "+ venda.filial.nome);
            } else {
                produto = result;
                carregaListaProd();
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("erro ao carregar lista de produtos");
        }
    });
}

/**
 * Listar produtos adicionando cada um em uma tag option
 */
function carregaListaProd() {
    atualizarListaProd();
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

function formValido() {
    var quantidade = $("#quantidade").val();
    var indiceProd = $("#listarProdutos").val();
    var indiceClien = $("#listarClientes").val();
    var form = "Selecione";

    indiceClien === 'n' ? form += "\nCliente" : "";

    indiceProd === 'n' ? form += "\nProduto" : "";

    quantidade < 1 ? form += "\nQuantidade" : "";

    if (form !== "Selecione") {
        alert(form);
        return false;
    } else {
        return true;
    }
}

/**
 * adicionar produtos ao carrinho de compras, atualizar a tabela e quantidade de produtos
 */
function adicionarProduto() {
    var quantidade = $("#quantidade").val();
    var indiceProd = $("#listarProdutos").val();
    var indiceClien = $("#listarClientes").val();
    if (formValido()) {
        var totalCar = 0.0;
        for (i = 0; i < quantidade; i++) {
            totalCar += produto[indiceProd].valor;
        }

//        this.produto[indiceProd].quantidadeNaVenda = quantidade; atualizar quantidade na hr da venda
        produtoCarrinho.push({

            quantidade: quantidade,
            produto: this.produto[indiceProd],
            VlrTotalCar: totalCar
        });
        atualizaVenda(this.cliente[indiceClien], totalCar);
        atualizaQtdProd(indiceProd, quantidade);
        fixarClienteComboBox();
        tabelaCarrinho();

        carregaListaProd();//apos atualizar a quantidade de produto local, carrega a lista de produto local.  Caso algum produto tenha sido casdastrado no momento da venda é necessário um refresh
        limpaForm();
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

/**
 * limpar o fomulario de venda
 */
function limpaForm() {
    $("#valorUnitario").text("Valor Unidade: R$ 0");
    $("#valorTotal").text("Valor Total: R$ 0");
    document.getElementById("quantidade").value = 0;
    document.getElementById("quantidade").max = 0;
}

/*
 * 
 * metodo para fixa cliente selecionado na comboBox
 */
function fixarClienteComboBox() {
    document.getElementById("listarProdutos").selectedIndex = 0;
    $("#listarClientes").hide();
    var indiceClien = $("#listarClientes").val();
    $("#cliSelecionado").text("CLIENTE: " + cliente[indiceClien].nome);
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
        coluna += '<td>' + produtoCarrinho[i].quantidade + '</td>';
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
    restauraLstProduto(i);
    produtoCarrinho.splice(i, 1);
    tabelaCarrinho();
    carregaListaProd();
}

/*
 * complemento para excluir item
 * responsavel por voltar a quantidade do produto em "estoque" e deixa o produto disponivel para venda novamente
 */
function restauraLstProduto(i) {

    const indice = produto.indexOf(produtoCarrinho[i].produto);
    if (indice > -1) {
        produto[indice].quantidade += eval(produtoCarrinho[i].quantidade);
    } else {
        produtoCarrinho[i].produto.quantidade += eval(produtoCarrinho[i].quantidade);
        produto.push(produtoCarrinho[i].produto);
    }
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
            alert("Item excluido com sucesso!");
        }
    });
}

function getProdVenda() {
    venda.produtos = [];
    for (i = 0; i < produtoCarrinho.length; i++) {
        const indice = venda.produtos.indexOf(produtoCarrinho[i].produto);
        if (indice > -1) {
            venda.produtos[indice].quantidadeNaVenda += eval(produtoCarrinho[i].quantidade);
            console.log(venda.produtos[indice].quantidadeNaVenda);
        } else {
            produtoCarrinho[i].produto.quantidadeNaVenda += eval(produtoCarrinho[i].quantidade);
            venda.produtos.push(produtoCarrinho[i].produto);
        }
        console.log(venda);

    }


}

function gerarVenda() {
    if (venda.cliente !== undefined) {
        if (venda.total > 0) {
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
                    alert("Erro na solicitação ao finalizar venda!");
                },
                success: function (result) {
                    if (result === '200') {
                        alert("Venda finalizada com sucesso");
                        window.location.reload();
                    } else if (result === '500') {
                        alert("Erro no servidor ao finalizar venda");
                    } else {
                        alert("Erro no servidor ao processar venda, revise o carrinho");
                    }
                }
            });
        } else {
            alert("Total Carrinho não pode ser 0");
        }
    } else {
        alert("erro ao obter cliente e produto");
    }
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