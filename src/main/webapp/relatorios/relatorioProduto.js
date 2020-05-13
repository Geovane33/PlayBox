
var relatorio = {};
var carregou = true;
var filial = {};
$(document).ready(() => {
    init();

//    function () {
//
//        $("#output").pivotUI(
//                [
//                    {color: "blue", shape: "circle"},
//                    {color: "red", shape: "triangle"}
//                ],
//                {
//                    rows: ["color"],
//                    cols: ["shape"]
//                }
//        );
//    }
//    ;
});

/**
 * metodo manter a tabela "atualizada" remove todas as linhas a cada vez que atualiza a linhasTBV
 */
//function removeLinha() {
//    i = document.querySelectorAll("tr").length - 2;
//    for (; i > 0; i--) {
//        document.getElementById('tableProd').getElementsByTagName('tr')[0].remove();
//    }
//}


function init() {
    getFilial();
    obterRelatorio();
    tabela();

}

function getFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    if (filial === null) {
        $('#tabelaRelatorio').hide();
        alert("Erro ao obter filial");
        window.location.href = '../';
    } else {
        $('#tabelaRelatorio').show();
    }
}

function carregaTabela() {
//    removeLinha();
    for (var i = 0; i < relatorio.length; i++) {
        insereCadastro(relatorio[i]);
//        var linha = $("<tr>");
//        var coluna = "";
//        coluna += '<td>' + relatorio[i].filial.nome + '</td>';
//        coluna += '<td>' + relatorio[i].cliente.nome + '</td>';
//        coluna += '<td>' + relatorio[i].total + '</td>';
////        coluna += '<td>' + dataAtualFormatada(relatorio[i].dataVenda) + '</td>';
////        coluna += '<td><img class="imgDel" src="../icons/baseline_delete_forever_black_18dp.png" onClick="excluirProd(' + i + ', ' + produto[i].id + ')"></td>';
////        coluna += '<td><img class="imgDel" src="../icons/outline_edit_black_18dp.png" onClick="editarProd(' + i + ')"></td>';
//        linha.append(coluna);
//        $("#tabelaRelatorio").append(linha);
    }
}


function tabela() {
    $('#tabela').DataTable();
}
function obterRelatorio() {
    $('#obterRelatorio').click(() => {
//        consultarClientes();
    });
    consulta = $("#campo").val();
    $.ajax({
        type: 'GET',
        url: '../notestore?controller=Relatorio&acao=consultar&idFilial=' + filial.id,

        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        }, error: function (jqXHR, textStatus, errorThrown) {
            alert("erro na solicitação");
        },
        success: function (result) {
            if (result === "" && carregou) {
                carregou = false;
                alert("Nenhuma venda encontrada para gerar relatorio");
            } else {
                carregou = false;
                relatorio = result;
                console.log(relatorio);
                carregaTabela();
            }
        }});
}


var selectedRow = null

function cadastrar() {
    if (validate()) {
        var produto = leiaDados();
        
        if (selectedRow == null) {
            insereCadastro(produto);

        } else {
            editaCadastro(produto);
        }
        resetForm(); 
    }
}

// Cria um objeto produto com os dados preenchidos
function leiaDados() {
    var produto = {}; // Criando o objeto

    // Inserindo seus atributos
    produto["Nome"] = document.getElementById("nome").value;
    produto["Tipo"] = document.getElementById("tipo").value;
    produto["Marca"] = document.getElementById("marca").value;
    produto["Descricao"] = document.getElementById("desc").value;
    produto["Quantidade"] = document.getElementById("qtd").value;
    produto["Preco"] = "R$ "+document.getElementById("preco").value;
    produto["DataDeEntrada"] = document.getElementById("dataEnt").value;
    // ---

    return produto;
}

// Insere os dados na Tabela
function insereCadastro( relatorio ) {
    var table = document.getElementById("tabela").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);

    cell1 = newRow.insertCell(0); // Nome
    cell1.innerHTML = relatorio.filial.nome;
    cell2 = newRow.insertCell(1); // Tipo
    cell2.innerHTML = relatorio.cliente.nome;
    cell3 = newRow.insertCell(2); // Marca
    cell3.innerHTML = relatorio.total;
//    cell4 = newRow.insertCell(3); // Descrição
//    cell4.innerHTML = relatorio.Descricao;
//    cell5 = newRow.insertCell(4); // Quantidade
//    cell5.innerHTML = relatorio.Quantidade
//    cell6 = newRow.insertCell(5); // Preço
//    cell6.innerHTML = relatorio.Preco
//    cell7 = newRow.insertCell(6); // Data de entrada
//    cell7.innerHTML = relatorio.DataDeEntrada

//    cell7 = newRow.insertCell(3); // Botões ( Editar ) e ( Deletar )
//    cell7.innerHTML = `<a onClick="onEdit(this)"> Editar </a>
//                       <a onClick="onDelete(this)"> Deletar </a>`;
}

// Limpa os campos
function resetForm() {
    document.getElementById("nome").value = "";
    document.getElementById("tipo").value = "";
    document.getElementById("marca").value = "";
    document.getElementById("desc").value = "";
    document.getElementById("qtd").value = "";
    document.getElementById("preco").value = "";
    document.getElementById("dataEnt").value = "";
    selectedRow = null;
}

// Responsavel por jogar os dados nos campos para serem alterados
function onEdit( td ) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("nome").value = selectedRow.cells[0].innerHTML;
    document.getElementById("tipo").value = selectedRow.cells[1].innerHTML;
    document.getElementById("marca").value = selectedRow.cells[2].innerHTML;
    document.getElementById("desc").value = selectedRow.cells[3].innerHTML;
    document.getElementById("qtd").value = selectedRow.cells[4].innerHTML;
    document.getElementById("preco").value = selectedRow.cells[5].innerHTML;
    document.getElementById("dataEnt").value = selectedRow.cells[6].innerHTML;
}

// Insere na lista o produto alterado
function editaCadastro( produto ) {
    selectedRow.cells[0].innerHTML = produto.Nome;
    selectedRow.cells[1].innerHTML = produto.Tipo;
    selectedRow.cells[2].innerHTML = produto.Marca;
    selectedRow.cells[3].innerHTML = produto.Descricao;
    selectedRow.cells[4].innerHTML = produto.Quantidade;
    selectedRow.cells[5].innerHTML = produto.Preco;
    selectedRow.cells[6].innerHTML = produto.DataDeEntrada;
}

// Resonsavel por deletar o produto desejado
function onDelete( td ) {
    if ( confirm('Tem certeza que deseja deletar este produto ?') ) {
        row = td.parentElement.parentElement;
        document.getElementById("listaCadastros").deleteRow(row.rowIndex);
        resetForm();
    }
}


function validate() {
    isValid = true;
    if (document.getElementById("nome").value == "") {
        isValid = false;
        document.getElementById("fullNameValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
            document.getElementById("fullNameValidationError").classList.add("hide");
    }
    return isValid;
}