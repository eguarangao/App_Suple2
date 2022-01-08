/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.DetailOrderDeleted;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class DetailOrderDeletedDao {

    Conection conex;
    String sql = "";

    public DetailOrderDeletedDao() {
        conex = new Conection();
    }

    public boolean insertDetailOrderDeleted(DetailOrderDeleted dOd) {
        sql = String.format("insert into detalle_pedido_eliminado "
                + "(cantidad, precio_unit, "
                + "encabezado_pedido_eliminado_id_pedeliminado,"
                + "producto_id_producto)"
                + "values(%s, %s, "
                + "(select id_pedeliminado from encabezado_pedido_eliminado "
                + "order by id_pedeliminado desc limit 1), %s)",
                dOd.getQuantity(), dOd.getPrice(),
                dOd.getId_product());
        return conex.modifyBD(sql);
    }

    public DefaultTableModel getProductos(String id_order) {
        sql = "select * from detalle_pedido "
                + "where encabezado_pedido_id_encapedido = " + id_order + "";
        System.out.println(sql);
        return conex.returnRecord(sql);
    }

}
