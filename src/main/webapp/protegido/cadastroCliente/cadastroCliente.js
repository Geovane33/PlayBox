//variaveis globais
var cliente = {};
var dataNascimento = null;
var consultaTipo = 'nome';
var filial;
var filiais = [];
var telas = [];
var expan = false;
var carregou = true;
var consultar = true;
$(document).ready(function () {
    init();
});
function init() {
    obterTelas();
    form();
    expand();
    setMask();
    setTimeout(function () {
        getFilialSelecionada();
        consultarClientes();
    }, 280);

}

function loadTime() {
    let timerInterval
    Swal.fire({
        title: 'Atualizando cliente!',
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
    })
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
                    title: 'Cliente cadastrado com sucesso',
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(function () {
                    window.location.reload();
                }, 1500);
            } else if (result === '200-2') {
                Swal.fire({
                    icon: 'success',
                    title: 'Cliente atualizado com sucesso',
                    showConfirmButton: false,
                    timer: 1500
                })
                setTimeout(function () {
                    window.location.reload();
                }, 1500);

            } else if (result === '2') {
                Swal.fire({
                    icon: 'error',
                    title: 'O CPF digitado ja tem cadastro',
                    showConfirmButton: true
                })
            } else if (result === '500') {
                Swal.fire({
                    icon: 'error',
                    title: 'erro no servidor ao cadastrar cliente',
                    showConfirmButton: true
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'erro ao processar os dados do cliente, revise os campos',
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



function setMask() {
    $('#nascimento').mask("00/00/0000", {placeholder: "dd/mm/aaaa"});
    var trocarMask = function (val) {
        return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
    },
            sp = {
                onKeyPress: function (val, e, field, options) {
                    field.mask(trocarMask.apply({}, arguments), options);
                }
            };
    $('#telefone').mask(trocarMask, sp);
    $('#CEP').mask('ZZZZZ-ZZZ', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: false
            }
        },
        placeholder: "_____-___"
    });
    $('#CPF').mask('ZZZ.ZZZ.ZZZ-ZZ', {
        translation: {
            'Z': {
                pattern: /[0-9]/, optional: false
            }
        },
        placeholder: "___.___.___-__"
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
            url: '../../notestore?controller=Cliente&acao=consultar',
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
                        'Não foi possivel consultar os clientes.',
                        'error');
            },
            success: function (result) {
                Swal.disableButtons();
                Swal.disableLoading();
                Swal.hideLoading();
                consultar = true;
                if (result === "" && carregou) {
                    carregou = false;
                    Swal.fire('Nenhum cliente cadastrado!',
                            'Cadastre seus clientes.',
                            'warning');
                } else if (result.length === 0) {
                    Swal.fire({
                        title: 'Nenhum cliente encontrado!',
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
                cliente = result;
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
    for (var i = 0; i < cliente.length; i++) {
        $('.tabela').show();
        $('.inputConsult').show();
        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + cliente[i].nome + '</td>';
        coluna += '<td>' + cliente[i].cpf + '</td>';
        coluna += '<td>' + cliente[i].dataNascimento + '</td>';
        coluna += '<td>' + cliente[i].email + '</td>';
        coluna += '<td>' + cliente[i].cidade + '</td>';
        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirCliente(this ,' + cliente[i].id + ')"></td>';
        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarCliente(' + i + ')"></td>';
        linha.append(coluna);
        $("#tableClientes").append(linha);
    }
}

function editarCliente(indice) {
    document.getElementById("id").value = cliente[indice].id;
    document.getElementById("idFilial").value = cliente[indice].idFilial;
    document.getElementById("nome").value = cliente[indice].nome;
    document.getElementById("CPF").value = cliente[indice].cpf;
    document.getElementById("nascimento").value = cliente[indice].dataNascimento;
    document.getElementById("sexo").value = cliente[indice].sexo;
    document.getElementById("telefone").value = cliente[indice].telefone;
    document.getElementById("email").value = cliente[indice].email;
    document.getElementById("uf").value = cliente[indice].uf;
    document.getElementById("cidade").value = cliente[indice].cidade;
    document.getElementById("CEP").value = cliente[indice].cep;
    document.getElementById("bairro").value = cliente[indice].bairro;
    document.getElementById("numero").value = cliente[indice].numero;
    document.getElementById("cadastrar").value = "atualizar";
    document.getElementById("enviar").value = "atualizar";
}

function excluirCliente(td, idCli) {
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
                    url: '../../notestore?controller=Cliente&acao=excluir&id=' + idCli,
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
                                title: 'Cliente excluído com sucesso!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            linha = td.parentElement.parentElement;
                            document.getElementById("tableClientes").deleteRow(linha.rowIndex - 1);
                        } else {
                            Swal.fire(
                                    'Erro ao excluir!',
                                    'Cliente possui vendas ativas.',
                                    'error');
                        }
                    }});
            }
        }])
}




function validarForm() {
//Valida o formulário
    $("#formCad").validate();
    //personalizar mensagens
    $.validator.addMethod("NOME", $.validator.methods.required,
            "NOME obrigatorio");
    $.validator.addMethod("CPF", $.validator.methods.required,
            "CPF obrigatorio");
    $.validator.addMethod("NASCIMENTO", $.validator.methods.required,
            "NASCIMENTO obrigatorio");
    $.validator.addMethod("SEXO", $.validator.methods.required,
            "SEXO obrigatorio");
    $.validator.addMethod("EMAIL", $.validator.methods.required,
            "EMAIL obrigatorio");
    $.validator.addMethod("UF", $.validator.methods.required,
            "UF obrigatorio");
    $.validator.addMethod("CIDADE", $.validator.methods.required,
            "CIDADE obrigatorio");
    // personalizar tamanho e mensagens dos cmapos
    $.validator.addMethod("NOME2", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    // combina os dois, aplicando as regras nos campos que contenham a classe "NOME"
    $.validator.addClassRules("NOME", {NOME: true, NOME2: 3}); //NOME REQUERIDO --- NOME2 MININO DE CARACTERES


    $.validator.addMethod("CPF2", $.validator.methods.minlength,
            $.validator.format("Minimo 11 caracteres"));
    $.validator.addClassRules("CPF", {CPF: true, CPF2: 14});
    $.validator.addMethod("NASCIMENTO2", $.validator.methods.minlength,
            $.validator.format("Minimo 8 caracteres"));
    $.validator.addClassRules("NASCIMENTO", {NASCIMENTO: true, NASCIMENTO2: 10});
    $.validator.addMethod("SEXO2", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    $.validator.addClassRules("SEXO", {SEXO: true, SEXO2: 4});
    $.validator.addMethod("EMAIL2", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    $.validator.addClassRules("EMAIL", {EMAIL: true, EMAIL2: 5});
    $.validator.addMethod("UF2", $.validator.methods.maxlength,
            $.validator.format("Selecione o UF"));
    $.validator.addClassRules("UF", {UF: true, UF2: 2});
    $.validator.addMethod("CIDADE2", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    $.validator.addClassRules("CIDADE", {CIDADE: true, CIDADE2: 4});
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
    console.log(telas[0].tela);
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
                if (telas[0]) {
                    $('.corpo').show();
                    carregarTelas();
                } else if(telas[1]){
                       window.location.href = '../cadastroProduto/cadastroProduto.jsp';
                }else{

                
              
                 


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
