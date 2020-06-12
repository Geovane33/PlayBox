
var produto = [];
var telas = [];
var filial = {};
var expan = false;
function init() {
    setTimeout(function () {
        obterTelas();
        getFilial();
        consultarProdutos();
    }, 280);

    expand();
    form();
    mask();
    ClickConsulta();
}



function mask() {
    $('#dataEnt').mask('00/00/0000',
            {'translation':
                        {0: {pattern: /[0-9*]/}, optional: false}, placeholder: '__/__/__'});
}

$(document).ready(function () {
    init();
});

function ClickConsulta() {
    $('#consultarProd').click(() => {
        consultarProdutos();
    }
    );
}

function consultarProdutos() {
    $.ajax({
        type: 'GET',
        url: '../../notestore?controller=Produto&acao=consultar&idFilial=' + filial.id,
        beforeSend: function (xhr) {
            loadMsg("Carregando!");
        },
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        success: function (result) {
            if (result === '') {
                Swal.fire({
                    icon: 'warning',
                    title: 'Nenhum produto cadastrado',
                    showConfirmButton: false,
                    timer: 1000
                });
            } else if (result === "403") {
                Swal.fire({
                    icon: 'warning',
                    title: 'Não autorizado',
                    showConfirmButton: false,
                    timer: 1000

                });
                setTimeout(function () {
                    window.location.href = '../../protegido/index.html';
                }, 900);

            } else {
                Swal.fire({
                    showConfirmButton: false,
                    timer: 1
                });
                produto = result;
                carregaTabela();
            }
        }});
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
        coluna += '<td>' + produto[i].dataDeEntrada + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirProd(' + i + ', ' + produto[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarProd(' + i + ')"></td>';
        linha.append(coluna);
        $("#listaCadastros").append(linha);
    }
}

function editarProd(indice) {
    document.getElementById("id").value = produto[indice].id;
    document.getElementById("nome").value = produto[indice].nome;
    document.getElementById("marca").value = produto[indice].marca;
    document.getElementById("desc").value = produto[indice].descricao;
    document.getElementById("qtd").value = produto[indice].quantidade;
    document.getElementById("valor").value = produto[indice].valor;
    document.getElementById("dataEnt").value = produto[indice].dataDeEntrada;
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

    Swal.queue([{
            title: 'Você tem certeza',
            text: "Você não poderá reverter isso!",
            icon: 'warning',
            showLoaderOnConfirm: true,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim!',
            cancelButtonText: 'Não',
            preConfirm: () => {
                return $.ajax({
                    type: 'GET',
                    url: '../../notestore?controller=Produto&acao=excluir&id=' + idProd,
                    beforeSend: function (xhr) {
                        loadMsg("Excluindo!");
                    },
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Erro ao excluir Produto',
                            showConfirmButton: true
                        })
                    },
                    success: function (result) {
                        produto.splice(i, 1);
                        carregaTabela();
                        Swal.fire({
                            icon: 'success',
                            title: 'Produto excluído com sucesso!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }});
            }
        }]);
}

function form() {
    validarForm();
    $('form').ajaxForm({
        onsubmit: function (event) {
        }, beforeSend: function (xhr) {
            loadMsg("Enviando!");
        },
        success: function (result, textStatus, jqXHR) {
            if (result !== '') {
                Swal.fire({
                    icon: 'success',
                    title: result,
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'erro no servidor ao processar dados',
                    showConfirmButton: true
                })
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                icon: 'error',
                title: 'Erro ao solicitar',
                showConfirmButton: true
            })
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

    $.validator.addMethod("cMinlength", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    // combina os dois, aplicando as regras nos campos que contenham a classe chamada "cliente"
    $.validator.addClassRules("quantidade", {cRequired: true, cMinlength: 1});
}
function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));

    if (filial === null) {
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
    document.getElementById("idFilial").value = filial.id;
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

function loadMsg(msg) {
    Swal.fire({
        title: msg,
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });
}

function carregarTelas() {
    if (telas[0]) {
        $("#listNav").append('<li>' +
                ' <a href="../cadastroCliente/cadastroCliente.jsp">' +
                '<div class="menu-item"> ' +
                ' <span class="icon clientes"></span>' +
                '<span class="description">CLIENTES</span> ' +
                '</div>' +
                '</a>' +
                ' </li>');
    }
    if (telas[1]) {
        $("#listNav").append('<li>' +
                ' <a href="../cadastroProduto/cadastroProduto.jsp">' +
                '<div class="menu-item"> ' +
                ' <span class="icon produtos"></span>' +
                '<span class="description">PRODUTOS</span> ' +
                '</div>' +
                '</a>' +
                ' </li>');
    }

    if (telas[2]) {
        $("#listNav").append('<li>' +
                '<a href="../vendas/realizarVenda.jsp">' +
                '<div class="menu-item">' +
                '<span class="icon vendas"></span>' +
                '<span class="description">VENDAS</span> ' +
                '</div>' +
                '</a>' +
                '</li>');
    }
    if (telas[3]) {
        $("#listNav").append('<a href="../relatorios/relatorio.jsp">' +
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
                if (telas[1]) {
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