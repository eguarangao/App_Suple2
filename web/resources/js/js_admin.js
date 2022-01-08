/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global angular */

app = angular.module('app', []);
app.controller('controllerAdmin', function ($scope, $http) {

    $scope.datosTienda = [];

    $(document).ready(function () {
        getDatosSession();
        $scope.getUsuarios();
    });

    function getDatosSession() {
        $.ajax({
            type: "POST",
            url: 'SessionServlet',
            data: {"opcion": "ses"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                rdata = JSON.parse(data);
                datasession = rdata;
                console.log(rdata);
                if (rdata.redirect === "index.html") {
                    location.href = "index.html";
                } else {
                    $("#nombre_usuario").html(rdata.userObject.getNombreUsuario);
                    $("#cargo").html(rdata.userObject.getCargo);

                    if (rdata.userObject.getCargo === "tienda") {
                        location.href = "tienda.html";
                    } else if (rdata.userObject.getCargo === "cliente") {
                        location.href = "cliente.html";
                    }

                }
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    //cerrar sesion
    $("#btn-cerrar-sesion").click(function () {
        cerrarSesion();
    });

    $scope.getUsuarios = function () {
        $.ajax({
            type: "POST",
            url: 'UsuarioServlet',
            data: {"opcion": "datosUsuarios"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.datosTienda = JSON.parse(data);
                    console.log($scope.datosTienda);
                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    };

    $scope.habilitarUsuario = function (id_usuario) {
        $.ajax({
            type: "POST",
            url: 'UsuarioServlet',
            data: {"opcion": "habilitarUsuario", "id_usuario": id_usuario},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.getUsuarios();
                    swal.fire("Excelente!", data, "success");
                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

});


