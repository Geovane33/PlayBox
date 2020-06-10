/**
 * chamar o init ao carregar pagina
 */
$(document).ready(function () {
    init();
});
var expan = false;
var venda = {total: 0};
var produtoCarrinho = [];
var filiais = [];
var cliente = [];
var produto = [];
function init() {
    setTimeout(function () {
        getFilialSelecionada();
        expand();
        obterFiliais();
        loadMsg("Carregando!");
        listarClientes();
        listarProduto();
        setTimeout(function () {
            Swal.fire({
                  showConfirmButton: false,
                timer: 1
            })
        }, 1000);

        limpaForm();
    }, 280);

}

/**
 * chamada no mesmo momento que a pagina carregar
 */
function getFilialSelecionada() {
    venda.filial = JSON.parse(sessionStorage.getItem('filial'));
    if (venda.filial === null) {
        let timerInterval
        Swal.enableLoading();
        Swal.fire({
            icon: 'error',
            title: 'Erro ao carregar filial!',
            html: 'Direciando para o inicio em <b></b> milliseconds.',
            timer: 1300,
            showConfirmButton: false,
            timerProgressBar: true,
            onBeforeOpen: () => {
                Swal.enableLoading();
                timerInterval = setInterval(() => {
                    Swal.enableLoading();
                    const content = Swal.getContent()
                    if (content) {
                        const b = content.querySelector('b')
                        if (b) {
                            b.textContent = Swal.getTimerLeft()
                        }
                    }
                }, 100)
            },
            onClose: () => {
                clearInterval(timerInterval)
                window.location.href = '../index.html';
            }
        }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
                window.location.href = '../index.html';
            }
        })
    } else {
        $('.corpo').show();
    }
}

/**
 * Listar clientes adicionada cada um em uma tag option
 */
function listarClientes() {
    $.ajax({
        type: 'GET',
        url: '../../notestore?controller=Cliente&acao=consultar',
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, beforeSend: function (xhr) {
            xhr.setRequestHeader('X-Consulta', "");
            xhr.setRequestHeader('X-ConsultaTipo', "nome");
        }, error: function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                title: 'Erro ao carregar lista de clientes!',
                icon: 'error'
            });
        },
        success: function (result) {
            if (result === undefined) {
                Swal.fire({
                    title: 'Nenhum cliente cadastrado!',
                    icon: 'warning'
                });
            } else {
                cliente = result;
                carregarListCli();
            }
        }}
    );
}

function carregarListCli() {
    for (i = 0; i < cliente.length; i++) {
        var lista = "";
        lista += '<option value="' + i + '">' + cliente[i].nome + '</option>';
        $("#listarClientes").append(lista);
    }
}


/**
 * resquest para receber uma lsita de produtos
 */
function listarProduto() {
    getFilialSelecionada();
    $.ajax({
        type: 'GET',
        url: '../../notestore?controller=Produto&acao=consultar&idFilial=' + venda.filial.id,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, success: function (result) {
            console.log(result);
            if (result === '') {
                Swal.fire({
                    title: 'Nenhum produto cadastrado!',
                    icon: 'warning'
                });
            } else {
                produto = result;
                carregaListaProd();
            }

        },
        error: function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                title: 'Erro ao carregar lista de produtos!',
                icon: 'error'
            });
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
        Swal.fire({
            title: form,
            icon: 'error'
        });
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
        produto[indice].quantidadeNaVenda = 0;
    } else {
        produtoCarrinho[i].produto.quantidade += eval(produtoCarrinho[i].quantidade);
        produtoCarrinho[i].produto.quantidadeNaVenda = 0;
        produto.push(produtoCarrinho[i].produto);
    }
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

function loadMsg(msg) {
    Swal.fire({
        title: msg,
          showConfirmButton: false,
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });
}

function gerarVenda() {
    if (venda.cliente !== undefined) {
        if (venda.total > 0) {
            venda.dataVenda = new Date();
            getProdVenda();
            getFilialSelecionada();
            $.ajax({
                type: 'POST',
                url: '../../notestore?controller=Venda&acao=adicionar',
                headers: {
                    accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"
                }, beforeSend: function (xhr) {
                    loadMsg("Finalizando venda.");
                    xhr.setRequestHeader('X-Venda', JSON.stringify(venda));
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    Swal.fire({
                        title: 'Erro na solicitação ao finalizar venda.',
                        icon: 'error'
                    });
                },
                success: function (result) {
                    if (result === '200') {

                        Swal.fire({
                            icon: 'success',
                            title: 'Venda finalizada com sucesso',
                            showConfirmButton: false,
                            timer: 1000
                        })
                        window.location.reload();
                    } else if (result === '500') {
                        Swal.fire({
                            title: 'Erro no servidor ao finalizar venda!',
                            icon: 'error'
                        });
                    } else if (result !== "") {
                                  Swal.fire({
                            title: result,
                            icon: 'error'
                        });
                    } else {
                        Swal.fire({
                            title: 'Erro no servidor ao processar venda, revise o carrinho!',
                            icon: 'error'
                        });
                    }
                }
            });
        } else {
            Swal.fire({
                title: 'Total Carrinho não pode ser 0!',
                icon: 'error'
            });
        }
    } else {
        Swal.fire({
            title: 'Erro ao obter cliente e produto!',
            icon: 'error'
        });
    }
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

function carregarTelas() {
    if (filiais.length === 1) {
        $("#listNav").append('<li>' +
                ' <a href="../cadastroCliente/cadastroCliente.jsp">' +
                '<div class="menu-item"> ' +
                ' <span class="icon clientes"></span>' +
                '<span class="description">CLIENTES</span> ' +
                '</div>' +
                '</a>' +
                ' </li>');
    } else {
        $("#listNav").append(
                '<li>' +
                ' <a href="../cadastroCliente/cadastroCliente.jsp">' +
                '<div class="menu-item"> ' +
                ' <span class="icon clientes"></span>' +
                '<span class="description">CLIENTES</span> ' +
                '</div>' +
                '</a>' +
                ' </li>' +
                '<li>' +
                ' <a href="../cadastroProduto/cadastroProduto.jsp">' +
                '<div class="menu-item"> ' +
                ' <span class="icon produtos"></span>' +
                '<span class="description">PRODUTOS</span> ' +
                '</div>' +
                '</a>' +
                ' </li>' +
                '<li>' +
                '<a href="../relatorios/relatorio.jsp">' +
                '<div class="menu-item">' +
                '<span class="icon relatorios"></span>' +
                '<span class="description">RELATÓRIOS</span> ' +
                '</div>' +
                ' </a>' +
                '</li> ');
    }
    $("#listNav").append('<li>' +
            '<a href="../../logout">' +
            '<div class="menu-item">  ' +
            '<span class="icon logout"></span>' +
            '<span class="description">LOGOUT</span>  ' +
            ' </div>' +
            '</a>' +
            '</li>');
}

function obterFiliais() {
    if (filiais.length === 0) {
        $.ajax({
            type: 'GET',
            url: '../../notestore?controller=Filial&acao=consultar',
            contentType: 'application/json;charset=UTF-8',
            headers: {
                Accept: "application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            },
            success: function (result) {
                filiais = result;
                carregarTelas();
            }});
    }
}
