//variaveis globais
var cliente = {};
var dataNascimento = null;
var consultaTipo = 'nome';
var filial;
var carregou = true;


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
            alert("error");
        },
        success: function (result) {
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
        coluna += '<td>' + dataAtualFormatada(cliente[i].dataNascimento) + '</td>';
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
    document.getElementById("nascimento").value = dataAtualFormatada(cliente[indice].dataNascimento);
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
        linha = td.parentElement.parentElement;
        document.getElementById("tableClientes").deleteRow(linha.rowIndex - 1);
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
                alert(result);
            }});
    } else {
        alert("cancelado");
    }
}

function dataAtualFormatada(data) {
    var novaData = new Date(data),
            dia = (novaData.getDate()).toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (novaData.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = novaData.getFullYear();
    return dia + "/" + mes + "/" + anoF;
}

function validarForm() {
    //Valida o formulário
    $("#formCad").validate();
    //apelido necessário para o cRequired com nova mensagem
    $.validator.addMethod("cRequired", $.validator.methods.required,
            "Campo obrigatorio");
    // apelido cMinlength
    $.validator.addMethod("cMinlength", $.validator.methods.minlength,
            $.validator.format("Minimo {0} caracteres"));
    // combina os dois, aplicando as regras nos campos que contenham a classe chamada "cliente"
    $.validator.addClassRules("cliente", {cRequired: true, cMinlength: 2});
}
