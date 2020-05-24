//variaveis globais
var cliente = {};
var dataNascimento = null;
var consultaTipo = 'nome';
var filial;
var carregou = true;
var consultar = true;
$(document).ready(function () {
    init();
});



function init() {
    form();
    setMask();
    getFilial();
    consultarClientes();
}

function form() {
    validarForm();
    $('form').ajaxForm({
        onsubmit: function (event) {
        },
        success: function (result, textStatus, jqXHR) {
            if (result === '200') {
                alert('cliente cadastrado com sucesso');
                window.location.reload();
            } else if (result === '200-2') {
                alert('Cliente atualizado com sucesso');
                window.location.reload();
            } else if (result === '2') {
                alert('O CPF digitado ja tem cadastro');
            } else if (result === '500') {
                alert('erro no servidor ao cadastrar cliente');
            } else {
                alert('erro ao processar os dados do cliente, revise os campos');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Erro ao solicitar');
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

function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        alert("Erro ao obter filial");
        window.location.href = '../';
    }
    document.getElementById("idFilial").value = filial.id;
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
            url: '../notestore?controller=Cliente&acao=consultar',

            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            }, beforeSend: function (xhr) {
                xhr.setRequestHeader('X-Consulta', consulta);
                xhr.setRequestHeader('X-ConsultaTipo', consultaTipo);
            }, error: function (jqXHR, textStatus, errorThrown) {
                consultar = true;
                alert("error");
                window.location.reload();
            },
            success: function (result) {
                consultar = true;
                if (result === "" && carregou) {
                    carregou = false;
                    alert("Nenhum cliente cadastrado");
                } else {
                    carregou = false;
                    cliente = result;
                    carregaTabela();
                }
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
    if (confirm("Deseja excluir?")) {
        $.ajax({
            type: 'GET',
            url: '../notestore?controller=Cliente&acao=excluir&id=' + idCli,
            headers: {
                Accept: "application/json; charset=utf-8",
                "Content-Type": "application/json; charset=utf-8"
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Erro ao excluir cliente");
            },
            success: function (result) {
                if (result === '200') {
                    alert('Cliente excluido com sucesso');
                    linha = td.parentElement.parentElement;
                    document.getElementById("tableClientes").deleteRow(linha.rowIndex - 1);
                } else {
                    alert(result);
                }
            }});
    } else {
        alert("cancelado");
    }
}

function validarForm() {
    //Valida o formulário
    $("#formCad").validate();
    //personalizar mensagens
    $.validator.addMethod("NOME", $.validator.methods.required,
            "NOME obrigatorio");
    1

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
    $.validator.addClassRules("NOME", {NOME: true, NOME2: 3});//NOME REQUERIDO --- NOME2 MININO DE CARACTERES


    $.validator.addMethod("CPF2", $.validator.methods.minlength,
            $.validator.format("Minimo 11 caracteres"));
    $.validator.addClassRules("CPF", {CPF: true, CPF2: 14});


    $.validator.addMethod("NASCIMENTO2", $.validator.methods.minlength,
            $.validator.format("Minimo 8 caracteres"));
    $.validator.addClassRules("NASCIMENTO", {NASCIMENTO: true, NASCIMENTO2: 10});


    $.validator.addMethod("SEXO2", $.validator.methods.minlength,
            $.validator.format("Minimo 4 caracteres"));
    $.validator.addClassRules("SEXO", {SEXO: true, SEXO2: 4});


    $.validator.addMethod("EMAIL2", $.validator.methods.minlength,
            $.validator.format("Minimo 5 caracteres"));
    $.validator.addClassRules("EMAIL", {EMAIL: true, EMAIL2: 5});


    $.validator.addMethod("UF2", $.validator.methods.maxlength,
            $.validator.format("Selecione o UF"));
    $.validator.addClassRules("UF", {UF: true, UF2: 2});


    $.validator.addMethod("CIDADE2", $.validator.methods.minlength,
            $.validator.format("Minimo 4 caracteres"));
    $.validator.addClassRules("CIDADE", {CIDADE: true, CIDADE2: 4});
}
