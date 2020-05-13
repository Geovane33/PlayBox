
var relatorio = {};
var carregou = true;
var filial = {};
var totalFilial;
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

function init() {
    getFilial();
    obterRelatorio();
    setTimeout(()=> tabela(), 500);


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
    for (var i = 0; i < relatorio.length; i++) {
        insereRelatorio(relatorio[i]);
//        TotalFilial();
    }
   
}

function totalFilial(relatorio){
    
}


function tabela() {
    $('#tabela').DataTable();
}
function obterRelatorio() {
    $('#obterRelatorio').click(() => {
//        consultarClientes();
    });
//    consulta = $("#campo").val();
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

// Insere os dados na Tabela
function insereRelatorio(relatorio) {
    var table = document.getElementById("tabela").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);

    cell1 = newRow.insertCell(0); // Nome
    cell1.innerHTML = relatorio.filial.nome;
    cell2 = newRow.insertCell(1); // nome do cliente
    cell2.innerHTML = relatorio.cliente.nome;
    cell3 = newRow.insertCell(2); //total por venda
    cell3.innerHTML = relatorio.total;
    cell4 = newRow.insertCell(3); //data da venda
    cell4.innerHTML = dataAtualFormatada(relatorio.dataVenda);
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
function onEdit(td) {
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
function editaCadastro(produto) {
    selectedRow.cells[0].innerHTML = produto.Nome;
    selectedRow.cells[1].innerHTML = produto.Tipo;
    selectedRow.cells[2].innerHTML = produto.Marca;
    selectedRow.cells[3].innerHTML = produto.Descricao;
    selectedRow.cells[4].innerHTML = produto.Quantidade;
    selectedRow.cells[5].innerHTML = produto.Preco;
    selectedRow.cells[6].innerHTML = produto.DataDeEntrada;
}

// Resonsavel por deletar o produto desejado
function onDelete(td) {
    if (confirm('Tem certeza que deseja deletar este produto ?')) {
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

function dataAtualFormatada(data) {
    var novaData = new Date(data),
            dia = (novaData.getDate()).toString(),
            diaF = (dia.length === 1) ? '0' + dia : dia,
            mes = (novaData.getMonth() + 1).toString(), //+1 pois no getMonth Janeiro começa com zero.
            mesF = (mes.length === 1) ? '0' + mes : mes,
            anoF = novaData.getFullYear();
    return dia + "/" + mes + "/" + anoF;
}