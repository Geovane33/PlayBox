$(document).ready(function () {
    form();
});

function msgPassword() {
    $(".message").text("");
}

function form() {
    $('form').ajaxForm({
        onsubmit: function (event) {
        },
        success: function (result, textStatus, jqXHR) {
            if (result === '401') {
                $(".message").text("Usuário ou senha inválido");
                $("#pass").val("");
            } else {
                window.location.href = 'protegido/index.html';
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Erro ao solicitar');
        }
    });
}