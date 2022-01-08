/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global angular */

app = angular.module('app', []);
app.controller('controllerIndex', function ($scope, $http) {

    $(document).ready(function () {
        $("#form-registrarse").hide();
        $("#form-login").show();

        $("#ip_nombres").prop('disabled', false);
        $("#ip_apellidos").prop('disabled', false);
        $("#ip_nombre_tienda").prop('disabled', true);
        $("#ip_nombre_tienda").val("");
    });

    $("#open-login").click(function () {
        $("#form-registrarse").hide();
        $("#form-login").show();
    });

    $("#open-registrarse").click(function () {
        $("#form-registrarse").show();
        $("#form-login").hide();
    });

    $("#id_tipo_usuario").change(function () {
        let valor_tipousuario = $("#id_tipo_usuario").val();
        console.log(valor_tipousuario);
        if (valor_tipousuario === "tienda") {
            $("#ip_nombres").prop('disabled', true);
            $("#ip_apellidos").prop('disabled', true);
            $("#ip_nombre_tienda").prop('disabled', false);
            $("#ip_nombre_tienda").val("");
        } else {
            $("#ip_nombres").prop('disabled', false);
            $("#ip_apellidos").prop('disabled', false);
            $("#ip_nombre_tienda").prop('disabled', true);
            $("#ip_nombre_tienda").val("");
        }
    });

    $("#btn-login").click(function () {
        $.ajax({
            type: "POST",
            url: 'SessionServlet',
            data: {"opcion": "log", "user": $("#nombre_user").val(), "pass": $("#password").val()},
            beforeSend: function () {
                cargando();
            },
            success: function (data)
            {
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
    });

    $("#btn-registrarse").click(function () {
        clearFormRegister();
        $("#newUser").modal();
    });

    $("#btn-guardar-employee").click(function () {

        if ($("#ip_contrasenia").val().trim() !== $("#ip_confirmarcontrasenia").val().trim()) {
            alert("Las contraseñas no coinciden");
            return;
        }

        let data = {
            nombres: $("#ip_nombres").val(),
            apellidos: $("#ip_apellidos").val(),
            nombre_tienda: $("#ip_nombre_tienda").val(),
            usuario: $("#ip_nombreusuario").val(),
            contrasenia: $("#ip_confirmarcontrasenia").val(),
            tipo_usuario: $("#id_tipo_usuario").val()
        };

        insertarUsuario(data);

    });

    function insertarUsuario(jsondata) {
        $.ajax({
            type: "POST",
            url: 'UsuarioServlet',
            data: {"opcion": "insertarusuario", ...jsondata},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                console.log(data);
                swal.fire("Excelente!", data.message, "success");
                clearFormRegister();
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    function clearFormRegister() {
        $("#ip_nombres").val("");
        $("#ip_apellidos").val("");
        $("#ip_cedula").val("");
        $("#ip_telefono").val("");
        $("#ip_nombreusuario").val("");
        $("#ip_contrasenia").val("");
        $("#ip_confirmarcontrasenia").val("");
    }

});
