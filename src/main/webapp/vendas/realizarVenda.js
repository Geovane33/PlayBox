var venda = null;

$(document).ready(function () {
    listarClientes();
    listarProduto();
    $('#verCarrinho').click(function () {
        if (venda === null) {

            $.ajax({
                type: 'GET',
                url: 'VendaServlet',
                headers: {

                    Accept: "application/json; charset=utf-8",
                    "Content-Type": "application/json; charset=utf-8"

                },
                success: function (result) {

                    venda = JSON.stringify(result);
                    consele.log("primeiro carregamento");
                    linhasTBV();

                }
            });

        } else {

            console.log("segundo carregamento");
            linhasTBV();
        }

    });

});


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
            cliente = result;
            for (i = 0; i < cliente.length; i++) {
                var lista = "";
                lista += '<option value="' + cliente[i].id + '">' + cliente[i].nome + '</option>';
                $("#listarClientes").append(lista);
            }
        }}
    );

}
function listarProduto() {
    $.ajax({
        type: 'GET',
        url: '../CadastroProdutoServlet',
        headers: {
            Accept: "application/json; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("erro ao carregar lista de produtos");
        },
        success: function (result) {
            produto = result;
            for (i = 0; i < produto.length; i++) {
                var lista = "";
                lista += '<option value="' + i + '">' + produto[i].nome + '</option>';
                $("#listarProdutos").append(lista);
            }
        }});
}

function getValorProd() {
    var indice = $("#listarProdutos").val();
    if (indice === 'n') {
        $("#valorUnitario").text("Valor unidade: R$ 0");
        document.getElementById("quantidade").value = 0;
         document.getElementById("quantidade").max = 0;
    }else{
        $("#valorUnitario").text("Valor unidade: R$ " + produto[indice].valor);
          document.getElementById("quantidade").value = 1;
          document.getElementById("quantidade").max = produto[indice].quantidade;
    }
  
    getTotalVenda();
}

function getTotalVenda() {
    var indice = $("#listarProdutos").val();
    var quantidade = $("#quantidade").val();
    var total = 0.0;
    for (i = 0; i < quantidade; i++) {
        total += produto[indice].valor;
    }
    $("#valorTotal").text("Valor total: R$ " + total);
}


function adicionarProduto(){
    
}



function linhasTBV() {
    for (var i = 0; i < venda.length; i++) {

        var linha = $("<tr>");
        var coluna = "";
        coluna += '<td>' + venda[i].produto + '</td>';
        coluna += '<td>' + venda[i].quantidadeNaVenda + '</td>';
        coluna += '<td>' + venda[i].dataVenda + '</td>';
        coluna += '<td>' + venda[i].valor + '</td>';
        coluna += '<td><a src="delete_forever_18dp.png" onclick="excluirItemCarrinho(' + venda[i].id + ')a>excluir<a></td>';
        linha.append(coluna);
        $("#vendasCon").append(linha);

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
            alert("Item excluido com sucesso!")
        }
    });
}


