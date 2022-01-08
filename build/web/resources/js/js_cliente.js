/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global angular */

app = angular.module('app', []);
app.controller('controllerCliente', function ($scope, $http) {

    $scope.datosTienda = [];
    $scope.listaProductos = [];


    $scope.detalle = [];
    $scope.valorTotalDetail = {
        "subtotal": 0,
        "iva": 0,
        "totalapagar": 0
    };
    $scope.descuento = {
        porcentaje: -1
    };
    var id_tienda_selelccionada = "-1";
    var subtotal = 0;
    var iva = 0;
    var totalapagar = 0;

    $(document).ready(function () {
        getDatosSession();
        $scope.getTablaTiendas();
        $scope.cargarPedidos();
        $scope.cargarDescuento();
        $scope.cargarPedidosCancelados();
        $("#frm1").hide();
        $("#frm0").show();
    });

    $("#btn1").click(function () {
        $("#frm0").show();
        $("#frm1").hide();
    });

    $("#btn0").click(function () {
        $("#frm0").hide();
        $("#frm1").show();
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
                    } else if (rdata.userObject.getCargo === "admin") {
                        location.href = "admin.html";
                    }
                }
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.getTablaTiendas = function () {
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
                    //$scope.cargarProductos($scope.datosTienda[0].id_usuario);
                    //id_tienda_selelccionada = $scope.datosTienda[0].id_usuario;

                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    };

    $scope.cargarProductos = function (id_usuario) {
        $.ajax({
            type: "POST",
            url: 'ProductoServlet',
            data: {"opcion": "listarProductosTienda", "id_usuario": id_usuario},
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

    $("#ip_tienda").change(function () {
        $scope.cargarProductos($("#ip_tienda").val());
        id_tienda_selelccionada = $("#ip_tienda").val();
    });

    $scope.aniadir = function () {
        let position = parseInt($("#ip_codigo").val());
        let flag = true;

        let cantidad_a_comprar = parseFloat($("#ip_cantidad").val());
        let stock_actual = $scope.listaProductos[position].stock;

        if (cantidad_a_comprar > stock_actual) {
            alert("No existe stock suficiente para comprar esa cantidad");
            return;
        }
        console.log($scope.detalle.length);

        if ($scope.detalle.length > 0) {
            for (let i = 0; i < $scope.detalle.length; i++) {
                if ($scope.detalle[i].id_usuario !== $scope.listaProductos[position].usuario_id_usuario) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }

        if (flag) {
            $scope.detalle.push({
                "id": $scope.listaProductos[position].id_producto,
                "id_usuario": $scope.listaProductos[position].usuario_id_usuario,
                "producto": $scope.listaProductos[position].nombre,
                "cantidad": $("#ip_cantidad").val(),
                "precio": $scope.listaProductos[position].precio_unit
            });
            console.log($scope.detalle);
            calcularTotalAPagar({
                "cantidad": $("#ip_cantidad").val(),
                "precio": $scope.listaProductos[position].precio_unit
            }, "add");
            disminuirStock({
                "id_producto": $scope.listaProductos[position].id_producto,
                "cantidad": $("#ip_cantidad").val()
            });
            $("#addProduct").modal('hide');
        } else {
            alert("Debes comprar productos de una misma tienda")
        }
    }

    function disminuirStock(jsonData) {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "disminuiStock", ...jsonData},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                console.log(data);
                swal.fire("Excelente!", data, "success");
                $scope.cargarProductos(id_tienda_selelccionada);
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.eliminar = function (id_producto, cantidad, precio, index) {
        calcularTotalAPagar({
            "cantidad": cantidad,
            "precio": precio
        }, "del");
        aumentarStock({
            "id_producto": id_producto,
            "cantidad": cantidad
        }, index);

    };

    function aumentarStock(jsonData, index) {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "aumentarStock", ...jsonData},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                console.log(data);
                swal.fire("Excelente!", data, "success");
                $scope.detalle.splice(index, 1);
                $scope.cargarProductos(id_tienda_selelccionada);
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    function calcularTotalAPagar(jsonData, accion) {
        let precioprodcuto = parseFloat(jsonData.precio);
        let cantidad = parseFloat(jsonData.cantidad);
        if (accion === "add") {
            subtotal += subTotal({pc: precioprodcuto * cantidad});
            iva = calcularIva(subtotal);
            totalapagar = subtotal + iva;
        } else {
            subtotal -= subTotal({pc: precioprodcuto * cantidad});
            iva = calcularIva(subtotal);
            totalapagar = subtotal + iva;
        }

        console.log(subtotal + ' ' + iva + ' ' + totalapagar);
        $scope.totales = {
            "subtotal": "$ " + subtotal.toFixed(2),
            "iva": "$ " + iva.toFixed(2),
            "totalapagar": "$ " + totalapagar.toFixed(2)
        };
    }

    function subTotal(jsonValores) {
        return jsonValores.pc;
    }

    function calcularIva(subtotal) {
        return subtotal * 0.12;
    }

    function getDetalle() {
        let detalle = "";
        for (let i = 0; i < $scope.detalle.length; i++) {
            detalle += $scope.detalle[i].id + ";" + $scope.detalle[i].cantidad + ";" + $scope.detalle[i].precio;
            if (i < $scope.detalle.length - 1) {
                detalle += "/";
            }
        }
        console.log(detalle);
        return detalle;
    }

    $("#btn-realizarpedido").click(function () {
        let jsonPedido = {
            "detalle": getDetalle(),
            "total": $scope.totales.totalapagar.replace("$", "")
        };
        insertarPedido(jsonPedido);
    });

    function insertarPedido(jsonData) {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "insertarPedido", ...jsonData},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                console.log(data);
                swal.fire("Excelente!", data, "success");
                
                $scope.cargarDescuento();
                $scope.cargarPedidos();
                $scope.cargarPedidosCancelados();
                
                $("#frm0").hide();
                $("#frm1").show();
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.seleccionarProducto = function (position) {
        $("#addProduct").modal();
        $("#ip_codigo").val(position);
    };

    $scope.cargarDescuento = function () {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "cantidadDescuento"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.descuento.porcentaje = data;
                console.log($scope.descuento.porcentaje);
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    };

    //cerrar sesion
    $("#btn-cerrar-sesion").click(function () {
        cerrarSesion();
    });

    $scope.cancelarPedido = function (id_pedido, fecha_pedido, descuentos, total) {
        console.log(id_pedido);
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "cancelarPedido", "id_pedido": id_pedido, "fecha_pedido": fecha_pedido,
                "descuento": descuentos, "total": total},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                alert(data);
                $scope.cargarPedidos();
                $scope.cargarPedidosCancelados();
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
            data: {"opcion": "listarpedidos"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.datosPedido = JSON.parse(data);
                    console.log($scope.datosPedido);
                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

    $scope.cargarPedidosCancelados = function () {
        $.ajax({
            type: "POST",
            url: 'PedidoServlet',
            data: {"opcion": "listarpedidoscancelados"},
            beforeSend: function () {
                cargando();
            },
            success: function (data) {
                swal.close();
                $scope.$apply(function () {
                    $scope.datosPedidoCancelados = JSON.parse(data);
                    console.log($scope.datosPedidoCancelados);
                });
            },
            error: function (objXMLHttpRequest) {
                console.log("error", objXMLHttpRequest);
                swal.fire("!Oh no¡", "Se ha producido un problema.", "error");
            }
        });
    }

});
