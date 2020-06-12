$(document).ready(function () {
    init();
});

function init() {
    Swal.fire({
        icon: 'warning',
        title: 'Não autorizado',
        onBeforeOpen: () => {
            Swal.showLoading();
        }
    });
    setTimeout(function () {
        window.location.href = 'protegido/index.html';
    }, 1200);
}

