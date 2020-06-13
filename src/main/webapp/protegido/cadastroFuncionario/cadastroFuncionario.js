//variaveis globais
var funcionario = {};
var dataNascimento = null;
var consultaTipo = 'nome';
var filial;
var filiais = [];
var filiaiss = [];
var telas = [];
var expan = false;
var carregou = true;
var consultar = true;
$(document).ready(function () {
    init();
});
function init() {
    obterTelas();
    obterFiliais();
    form();
    expand();
    setTimeout(function () {
        getFilialSelecionada();
        consultarClientes();
    }, 280);

}

function loadTime() {
    let timerInterval
    Swal.fire({
        title: 'Atualizando funcionário!',
        timer: 300,
        timerProgressBar: true,
        onBeforeOpen: () => {
            timerInterval = setInterval(() => {
            }, 100)
        },
        onClose: () => {

            clearInterval(timerInterval)
        }
    }).then((result) => {

        if (result.dismiss === Swal.DismissReason.timer) {
            Swal.increaseTimer(1000);
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

function form() {
    validarForm();
    $('form').ajaxForm({

        onsubmit: function (event) {
        },
        beforeSend: function (xhr) {
            loadMsg("Enviando!");
        },
        success: function (result, textStatus, jqXHR) {
            if (result === '200') {
                Swal.fire({
                    icon: 'success',
                    title: 'Funcionário cadastrado com sucesso',
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else if (result === '200-2') {
                Swal.fire({
                    icon: 'success',
                    title: 'Funcionário atualizado com sucesso',
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(function () {
                    window.location.reload();
                }, 1500);

            } else if (result === '422') {
                Swal.fire({
                    icon: 'error',
                    title: 'O usuario digitado ja tem cadastro',
                    showConfirmButton: true
                })
            } else if (result === '500') {
                Swal.fire({
                    icon: 'error',
                    title: 'erro no servidor ao cadastrar funcionario',
                    showConfirmButton: true
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'erro ao processar os dados do funcionario, revise os campos',
                    showConfirmButton: true
                })
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            Swal.fire({
                icon: 'error',
                title: 'Erro ao solicitar',
                showConfirmButton: true,
                timer: 1200
            })
        }
    });
}


function getFilialSelecionada() {
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
    } else {
        document.getElementById("idFilial").value = filial.id;
    }

}

function consultarClientes() {
    $('#consultarCli').click(() => {
        consultarClientes();

    });
    if (consultar) {
        consultar = false;
        consulta = $("#campo").val();
        $.ajax({
            type: 'GET',
            url: '../../notestore?controller=Funcionario&acao=consultar',
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            }, beforeSend: function (xhr) {
                loadMsg("Carregando!");
                xhr.setRequestHeader('X-Consulta', consulta);
                xhr.setRequestHeader('X-ConsultaTipo', consultaTipo);
            }, error: function (jqXHR, textStatus, errorThrown) {
                consultar = true;
                Swal.fire(
                        'Erro ao consultar!',
                        'Não foi possivel consultar os funcionários.',
                        'error');
            },
            success: function (result) {
                Swal.disableButtons();
                Swal.disableLoading();
                Swal.hideLoading();
                consultar = true;
                if (result === "" && carregou) {
                    carregou = false;
                    Swal.fire({
                        title: 'Nenhum Funcionario cadastrado!',
                        icon: 'warning'
                    });
                } else if (result.length === 0) {
                    Swal.fire({
                        title: 'Nenhum Funcionario encontrado!',
                        icon: 'warning'
                    });
                    carregou = false;
                } else {
                    Swal.fire({
                        showConfirmButton: false,
                        timer: 1
                    });
                    carregou = false;
                }
                funcionario = result;
                carregaTabela();
            }});
    }

}

function buttonRadio(tipo) {
    this.consultaTipo = tipo;
    if (consultaTipo === 'CPF') {
        $('#campo').mask('ZZZ.ZZZ.ZZZ-ZZ', {
            translation: {
                'Z': {
                    pattern: /[0-9]/, optional: false
                }
            },
            placeholder: "___.___.___-__"
        });
    } else {
        $('#campo').unmask();
        $("#campo").attr("placeholder", "nome");
        $("#campo").attr("pattern", "[A-Za-z]{4,20}");
    }
}

function removeLinha() {
    i = document.querySelectorAll('tr').length - 2;
    for (; i > 0; i--) {
        document.getElementById('tableClientes').getElementsByTagName('tr')[0].remove();
    }
}

function carregaTabela() {
    removeLinha();
    for (var i = 0; i < funcionario.length; i++) {
        $('.tabela').show();
        $('.inputConsult').show();
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + funcionario[i].nome + '</td>';
        coluna += '<td>' + funcionario[i].usuario + '</td>';
        coluna += '<td>' + funcionario[i].funcao + '</td>';
        coluna += '<td>' + funcionario[i].salario + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirCliente(this ,' + funcionario[i].idUsuario + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarCliente(' + i + ')"></td>';
        linha.append(coluna);
        $("#tableClientes").append(linha);
    }
}

function editarCliente(indice) {
    document.getElementById("idFuncionario").value = funcionario[indice].id;
    document.getElementById("idUsuario").value = funcionario[indice].idUsuario;
    document.getElementById("idFilial").value = funcionario[indice].idFilial;
    document.getElementById("nome").value = funcionario[indice].nome;
    document.getElementById("usuario").value = funcionario[indice].usuario;
    document.getElementById("funcao").value = funcionario[indice].funcao;
    document.getElementById("salario").value = funcionario[indice].salario;
    document.getElementById("cadastrar").value = "atualizar";
}

function excluirCliente(td, idFuncionario) {
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
                    url: '../../notestore?controller=Funcionario&acao=excluir&idUsuario=' + idFuncionario,
                    beforeSend: function (xhr) {
                        loadMsg("Excluindo cliente!");
                    },
                    headers: {
                        Accept: "application/json; charset=utf-8",
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        Swal.fire(
                                'Erro ao excluir!',
                                'Não foi possivel enviar a solicitação ao servidor.',
                                'error');
                    },
                    success: function (result) {
                        if (result === '200') {
                            Swal.fire({
                                icon: 'success',
                                title: 'Funcionário excluído com sucesso!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            linha = td.parentElement.parentElement;
                            document.getElementById("tableClientes").deleteRow(linha.rowIndex - 1);
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: 'Erro ao excluír funcionário!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                    }});
            }
        }])
}

function validarForm() {
//Valida o formulário
    $("#formCad").validate();
    //personalizar mensagens
    $.validator.addMethod("filial", $.validator.methods.required,
            "Filial obrigatorio");
    $.validator.addMethod("NOME", $.validator.methods.required,
            "Nome obrigatorio");
    $.validator.addMethod("funcao", $.validator.methods.required,
            "Funcao obrigatorio");
    $.validator.addMethod("salario", $.validator.methods.required,
            "Salario obrigatorio");
    $.validator.addMethod("usuario", $.validator.methods.required,
            "Usuario obrigatorio");
    $.validator.addMethod("senha", $.validator.methods.required,
            "Senha obrigatorio");
    $.validator.addMethod("UF", $.validator.methods.required,
            "UF obrigatorio");
    $.validator.addMethod("CIDADE", $.validator.methods.required,
            "CIDADE obrigatorio");
    // personalizar tamanho e mensagens dos cmapos
    $.validator.addMethod("NOME2", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    // combina os dois, aplicando as regras nos campos que contenham a classe "NOME"
    $.validator.addClassRules("NOME", {NOME: true, NOME2: 3}); //NOME REQUERIDO --- NOME2 MININO DE CARACTERES

    $.validator.addClassRules("salario", {salario: true});

    $.validator.addClassRules("usuario", {usuario: true});

    $.validator.addClassRules("filial", {filial: true});

    $.validator.addClassRules("funcao", {funcao: true});

    $.validator.addMethod("senha2", $.validator.methods.minlength,
            $.validator.format("Minimo 6 caracteres"));
    $.validator.addClassRules("senha", {senha: true, senha2: 6});
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
            '<span class="description">SAIR</span>  ' +
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
                if (telas[4]) {
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
                    window.location.href = '../../NaoAutorizado.jsp';

                }
            }});
    }
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
                carregaListaProd();
            }});
    }
}

/**
 * Listar produtos adicionando cada um em uma tag option
 */
function carregaListaProd() {
    var lista = '<option value="">---</option>';
    $("#idFilial").append(lista);
    for (i = 0; i < filiais.length-1; i++) {
        console.log(filiais[i]);
        lista = "";
        lista += '<option value="' + filiais[i].id + '">' + filiais[i].nome + '</option>';
        $("#idFilial").append(lista);
    }
}