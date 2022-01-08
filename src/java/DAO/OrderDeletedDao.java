/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.OrderDeleted;

/**
 *
 * @author churri
 */
public class OrderDeletedDao {

    Conection conex;
    String sql = "";

    public OrderDeletedDao() {
        conex = new Conection();
    }

    public boolean insertOrderDeleted(OrderDeleted od) {
        sql = String.format("insert into encabezado_pedido_eliminado"
                + "(fecha_eliminar,fecha_pedido,usuario_id_usuario,descuento,total) "
                + "values(now(), '%s', %s, %s, %s)",
                od.getDate_order(), od.getId_user(),
                od.getDiscount(), od.getTotal());
        return conex.modifyBD(sql);
    }
}
