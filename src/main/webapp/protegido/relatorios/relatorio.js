//gerargraficos
var filialVendas = {
    nome: "",
    qtdVenda: 0,
    valor: 0
};
var totalPorFilial = [filialVendas];

var telas = [];

//tabela
var relatorio = [];
var carregou = true;
var filial = {};
var totalFilial = 0;
var controleTabela = -1;
var tabela2;
var expan = false;
var idCLientes = [];
$(document).ready(() => {
    init();
});

function init() {

    setTimeout(function () {
        loadMsg("Carregando!");
        expand();
        getFilial();
        obterRelatorio();
    }, 280);
}

function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        $("body").show();
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
                window.location.href = '../../protegido/index.html';
            }
        }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
                window.location.href = '../../protegido/index.html';
            }
        })
    }
}

function calculaTotalVenda(relatorio) {
    totalFilial += relatorio.total;
}

function calcTotalPorFilial() {

    for (var i = 0; i < relatorio.length; i++) {
        if (totalPorFilial.indexOf(relatorio[i].filial.nome) === -1) {
            totalPorFilial.push({nome: relatorio[i].filial.nome,
                qtdVendas: 1,
                valor: relatorio[i].total});
        } else {
            var indice = totalPorFilial.indexOf(relatorio[i].filial.nome);
            totalPorFilial[indice].valor += relatorio[i].total;
            totalPorFilial[indice].qtdVendas += 1;
        }
    }
    console.log(relatorio.length);
    console.log(totalPorFilial);
}

function carregaTabela() {
    for (var i = 0; i < relatorio.length; i++) {
        calculaTotalVenda(relatorio[i]);
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[i].filial.nome + '</td>';
        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
        coluna += '<td>R$ ' + relatorio[i].total + '</td>';
        coluna += '<td>' + relatorio[i].dataVenda + '</td>';
        coluna += '<td><input class="btnProduto" value=" ' + relatorio[i].produtos.length + '" type="button" onclick="getProdutos(' + i + ')"></td>';
        linha.append(coluna);
        $("#tabelaLinhas").append(linha);
    }
    $("#totalFilial").text("Valor Bruto Das Vendas: R$ " + totalFilial);
    $("#qtdVendas").text("Quantidade De Vendas Realizadas " + relatorio.length);
    tabelaDinamica();
}

function criarColunas() {
    $("#tabela2_wrapper").remove();
    var table = $("<table />", {
        id: 'tabela2'
    });
    $(".divTabela").append(table);

    var thead = $("<thead />", {
        id: 'thead'
    });

    $("#tabela2").append(thead);

    var coluna = ('<tr><th>Cliente</th>'
            + '<th>Nome</th>'
            + '<th>Marca</th>'
            + '<th>Quantidade</th>'
            + '<th>Preço</th></tr>');
    $("#thead").append(coluna);

    var tbody = $("<tbody />", {
        id: 'tabelaLinhasProd'
    });
    $("#tabela2").append(tbody);
}

function getProdutos(indice) {

    criarColunas();
    for (var i = 0; i < relatorio[indice].produtos.length; i++) {
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + relatorio[indice].cliente.nome + '</td>';
        coluna += '<td>' + relatorio[indice].produtos[i].nome + '</td>';
        coluna += '<td>' + relatorio[indice].produtos[i].marca + '</td>';
        coluna += '<td>' + relatorio[indice].produtos[i].quantidade + '</td>';
        coluna += '<td>R$ ' + relatorio[indice].produtos[i].valor + '</td>';
        linha.append(coluna);
        $("#tabelaLinhasProd").append(linha);
    }
    tabelaDinamica2();

}

function tabelaDinamica2() {
    tabela2 = $('#tabela2').DataTable({
        "language": {
            "url": "../jsons/produtos.json"
        }
    });
}

function tabelaDinamica() {
    $('#tabela').DataTable({
        "language": {
            "url": "../jsons/vendas.json"
        }
    });
    setTimeout(function () {
        Swal.fire({
            showConfirmButton: false,
            timer: 1
        })
        $(".corpo").show();
        $("body").show();
    }, 1);

}

function obterRelatorio() {
    $('#obterRelatorio').click(() => {
    });
    $.ajax({
        type: 'GET',
        url: '../../notestore?controller=Relatorio&acao=consultar&idFilial=' + filial.id,
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert("erro na solicitação");
        },
        success: function (result) {
            if (result === "" && carregou) {
                carregou = false;
                Swal.fire({
                    title: 'Nenhuma venda encontrada para gerar relatorio',
                    icon: 'warnig'
                });
            } else if (result === "403") {
                Swal.fire({
                    title: 'Não autorizado',
                    icon: 'error'
                });
            } else {
                carregou = false;
                relatorio = result;
                carregaTabela();
                calcTotalPorFilial();
            }
        }});
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
function obterTelas() {
    if (telas.length === 0) {
        $.ajax({
            type: 'GET',
            url: '../../telas',
            contentType: 'application/json;charset=UTF-8',
            headers: {
                Accept: "application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            },
            success: function (result) {
                telas = result;
                if (telas[3]) {
                    $('.corpo').show();
                    carregarTelas();
                } else {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Não autorizado',
                        onBeforeOpen: () => {
                            Swal.showLoading();
                        }
                    });
                    setTimeout(function () {
                        window.location.href = '../../protegido/index.html';
                    }, 1200);
                }
            }});
    }
}


