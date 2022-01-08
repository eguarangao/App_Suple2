/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var rdata;
var datasession = {};
function cargando() {
    let containerLoading = document.createElement('div');
    containerLoading.innerHTML = "<div class='spinner-border text-success' role='status'> <span class='sr-only'>Loading...</span></div> <br> <strong> Cargando... </strong>";
    swal.fire({
        width: 400,
        html: containerLoading,
        showConfirmButton: false,
        allowOutsideClick: false
    });
}

function cerrarSesion() {
    $.ajax({
        type: "POST",
        url: 'SessionServlet',
        data: {"opcion": "-1"},
        beforeSend: function () {
            cargando();
        },
        success: function (data) {
            swal.close();
            let rdata = JSON.parse(data);
            console.log(rdata);
            location.href = rdata.redirect;

        },
        error: function (objXMLHttpRequest) {
            console.log("error", objXMLHttpRequest);
            swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
        }
    });
}

$("#enlace-empleado").click(function () {
    if (datasession.userObject.getCargo === "admin") {
        location.href = "employee.html";
    } else {
        alert("No tienes permiso para esta opcion");
    }

    $("#enlaceinicio").click(function () {
        if (datasession.userObject.getCargo === "admin") {
            location.href = "home.html";
        } else {
            alert("No tienes permiso para esta opcion");
        }
    })
});


