
var filiais = [];
var filial;
var nomeFilial;

$(document).ready(function () {
    window.location.href="protegido/cadastroCliente/cadastroCliente.jsp";
    recebeFilial();
    obterFiliais();
    menu();
}
);

function carregarTelas() {
    if (filiais.length === 1) {
        $("#form").html('<a href="protegido/cadastroCliente/cadastroCliente.jsp" class="btn">Clientes</a> <br>' +
                '<a type="button" value="Vendas" href="protegido/vendas/realizarVenda.jsp" class="btn">Vendas</a>');
    } else {
        $("#form").html('<a href="protegido/cadastroCliente/cadastroCliente.jsp" class="btn">Clientes</a> <br>' +
                '<a href="protegido/cadastroProduto/cadastroProduto.jsp" class="btn">Produtos</a> <br>' +
                '<a type="button" value="Vendas" href="protegido/vendas/realizarVenda.jsp" class="btn">Vendas</a> <br>' +
                '<a type="button" value="Relatórios" href="protegido/relatorios/relatorio.jsp" class="btn">Relatório</a> <br>');
    }
}

function obterFiliais() {
    if (filiais.length === 0) {
        $.ajax({
            type: 'GET',
            url: 'notestore?controller=Filial&acao=consultar',
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

function recebeFilial() {
    filial = JSON.parse(sessionStorage.getItem('filial'));
    $("#idFilial").append(filial.nome);
    $("#form").show();
}

function menu(){ 
  $("#toggleMenu").on("click", function(){
      var menu = $("#navMenu");
      
    menu.toggleClass("collapsed");
    menu.toggleClass("expanded");
    });  
}
