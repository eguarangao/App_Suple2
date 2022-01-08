/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.DetailOrder;

/**
 *
 * @author churri
 */
public class DetailOrderDao {

    Conection conex;
    String sql = "";

    public DetailOrderDao() {
        conex = new Conection();
    }

    public boolean insertDetailOrder(DetailOrder detord) {
        sql = String.format("insert into detalle_pedido "
                + "(producto_id_producto, cantidad, "
                + "precio_unit, encabezado_pedido_id_encapedido) "
                + "values (%s, %s, %s, "
                + "(select id_encapedido from "
                + "encabezado_pedido order by id_encapedido desc limit 1))",
                detord.getId_product(),
                detord.getQuantity(), detord.getPrice());
        return conex.modifyBD(sql);
    }

    public boolean cancelOrder(DetailOrder detord) {
        sql = String.format("delte from "
                + "detalle_pedido where "
                + "encabezado_pedido_id_encapedido = %s",
                detord.getId_order());
        return conex.modifyBD(sql);
    }

    public boolean decreaseStock(DetailOrder detord) {
        sql = String.format("update producto set "
                + "stock = stock - %s where id_producto = %s",
                detord.getQuantity(), detord.getId_product());
        return conex.modifyBD(sql);
    }

    public boolean increaseStock(DetailOrder detord) {
        sql = String.format("update producto "
                + "set stock = stock + %s where id_producto = %s",
                detord.getQuantity(), detord.getId_product());
        return conex.modifyBD(sql);
    }

}
