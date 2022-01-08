/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


app = angular.module('app', []);
app.controller('controllerTienda', function ($scope, $http) {

    $scope.listaProductos = [];
    $scope.listaPedidos = [];

    $(document).ready(function () {
        getDatosSession();
        $scope.cargarProductos();
        $scope.cargarPedidos();
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

                    if (rdata.userObject.getCargo === "admin") {
                        location.href = "admin.html";
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

    $scope.cargarProductos = function () {
        $.ajax({
            type: "POST",
            url: 'ProductoServlet',
            data: {"opcion": "listarProductos"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.listaProductos = JSON.parse(data);
                    console.log($scope.listaProductos);
                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    };

    $("#btn-guardar-producto").click(function () {
        let dataProducto = {
            "nombre": $("#ip_nombre").val(),
            "stock": $("#ip_stock").val(),
            "precio": $("#ip_precio").val()
        };
        guardarProducto(dataProducto);
    });

    function guardarProducto(jsondata) {
        $.ajax({
            type: "POST",
            url: 'ProductoServlet',
            data: {"opcion": "insertarProducto", ...jsondata},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                console.log(data);
                swal.fire("Excelente!", data.message, "success");
                $("#newProduct").modal('hide');
                $scope.cargarProductos();
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.cargarPedidos = function () {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "listarpedidosTienda"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.listaPedidos = JSON.parse(data);
                    console.log($scope.listaPedidos);
                });
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

    $scope.despacharPedido = function (id_pedido) {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "despacharPedido", id_pedido: id_pedido},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                swal.fire("Excelente!", data, "success");
                $scope.cargarPedidos();
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.cancelarPedido = function (id_pedido, fecha_pedido, descuentos, total, id_usuario) {
        console.log(id_pedido);
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "cancelarPedidoTienda", "id_pedido": id_pedido, "fecha_pedido": fecha_pedido,
                "descuento": descuentos, "total": total, "id_usuario": id_usuario},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                swal.fire("Excelente!", data, "success");
                $scope.cargarPedidos();
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

});

