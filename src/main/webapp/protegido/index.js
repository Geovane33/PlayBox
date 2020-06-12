$(document).ready(function () {
    init();
});

var filiais = [];


function init() {
    obterFiliais();
  
    expand();
}

function atribuiFilial(idFilial) {
    filiais = filiais[idFilial].nome;
    var fi = $("<h1>");
    fi.append('Filial ' + filiais);
    $("#idFilial").html(fi);
}

function obterFiliais() {
    if (filiais.length === 0) {
        $.ajax({
            type: 'GET',
            url: '../notestore?controller=Filial&acao=consultar',
            contentType: 'application/json;charset=UTF-8',
            headers: {
                Accept: "application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            },
            success: function (result) {
                filiais = result;
                carregarFiliais();
            }});
    }
}


function carregarFiliais() {
    $("#formFiliais").html("<h3>Carregando filiais</h3>");
    var filial = $("<h3>");
    if (filiais.length < 1) {
        $("#formFiliais").html("<h3> Nenhuma filial cadastrada</h3>");
    } else {

        for (var i = 0; i < filiais.length; i++) {
            filial.append('<a class="btn" onclick="enviarFilial(' + i + ')">' + filiais[i].nome + '</a><br>');
        }
        $("#formFiliais").html(filial);
    }
}

function enviarFilial(indice) {
    if (filiais[indice].id === 0) {
        relatorioGeral();
    } else {
        var filial = JSON.stringify(filiais[indice]);
        sessionStorage.setItem('filial', filial);
        $('form').submit();
    }
}

function relatorioGeral() {
    var filial = {};
    filial.id = 0;
    filial.nome = 'Relatório Geral';
    filial.estado = 'Relatório Geral';
    sessionStorage.setItem('filial', JSON.stringify(filial));
    window.location.href = 'relatorios/relatorio.jsp';
}

function expand(){  
  $("#toggleMenu").on("click", function(){
      var menu = $("#navMenu");
      
    menu.toggleClass("collapsed");
    menu.toggleClass("expanded");
    });  
}

function obterFiliais() {
    if (filiais.length === 0) {
        $.ajax({
            type: 'GET',
            url: '../notestore?controller=Filial&acao=consultar',
            contentType: 'application/json;charset=UTF-8',
            headers: {
                Accept: "application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            },
            success: function (result) {
                filiais = result;
                  carregarFiliais();
                carregarTelas();
            }});
    }
}

function carregarTelas() {
    if (filiais.length === 1) {
        $("#listNav").html('');
    } else {
        $("#listNav").append(' <li>'+
                        '<a href="../protegido/index.html">'+
                          '  <div class="menu-item">       ' +
                         '       <span class="icon filiais"></span>'+
                             '   <span class="description">FILIAIS</span>'+
                          '  </div>'+
                   '     </a>'+
                   ' </li>'+
                 '   <li>'+
                      '  <a href="#">'+
                       '     <div class="menu-item">   '  +
                              '  <span class="icon funcionarios"></span>'+
                               ' <span class="description">FUNCIONARIOS</span>   '+    
                          '  </div>'+
                     '   </a>'+
                  '  </li>'+
                   ' <li>'+
                      '  <a href="#">'+
                           ' <div class="menu-item">  '      +
                              '  <span class="icon senha"></span>'+
                                '<span class="description">SENHA</span>   '+     
                          '  </div>'+
                     '   </a>'+
                  '  </li>'+
                    '<li>'+
                       ' <a onclick="relatorioGeral()">'+
                          '  <div class="menu-item">   '   +  
                             '   <span class="icon relatorios"></span>'+
                                '<span class="description">RELATÓRIOS</span>'      +  
                            '</div>'+
                       ' </a>' +
                    '</li> ');
    }
    $("#listNav").append('<li>' +
            '<a href="../logout">' +
            '<div class="menu-item">  ' +
            '<span class="icon logout"></span>' +
            '<span class="description">LOGOUT</span>  ' +
            ' </div>' +
            '</a>' +
            '</li>');
}