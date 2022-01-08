/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.Order;

/**
 *
 * @author churri
 */
public class OrderDao {

    Conection conex;
    String sql = "";

    public OrderDao() {
        conex = new Conection();
    }

    public boolean insertOrder(Order ord) {
        sql = String.format("insert into encabezado_pedido"
                + "(fecha_venta, estado, usuario_id_usuario, total, descuento)"
                + "values (now(),'%s',%s, %s, %s)",
                ord.getCondition(), ord.getId_user(),
                ord.getTotal(), ord.getDescuento());
        return conex.modifyBD(sql);
    }

    public String salesQuantityMonth(String id_user) {
        sql = String.format("select count(id_encapedido) \n"
                + "from encabezado_pedido "
                + "where DATE_PART('day', now() - fecha_venta) <= 30"
                + " and usuario_id_usuario = %s", id_user);
        return conex.fillString(sql);
    }

    public boolean cancelOrder(Order ord) {
        sql = String.format("delte from encabezado_pedido "
                + "where id_encapedido = %s", ord.getId_order());
        return conex.modifyBD(sql);
    }

    public String listOrder(String id_user) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta,"
                + " us.nombre_tienda, ep.total, ep.descuento, ep.estado\n"
                + "from encabezado_pedido as ep "
                + "inner join detalle_pedido as dp "
                + "on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr "
                + "on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us "
                + "on pr.usuario_id_usuario = us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_user + "\n"
                + "group by ep.total, ep.id_encapedido, "
                + "dp.id_detpedido, pr.id_producto, us.id_usuario "
                + "order by ep.id_encapedido asc";
        return conex.getRecordsInJson(sql);
    }

    public String listOrderCancel(String id_user) {
        sql = "select distinct ep.id_pedeliminado, ep.fecha_pedido, "
                + "ep.fecha_pedido, us.nombre_tienda, "
                + "ep.total, ep.descuento, ep.descuento\n"
                + "from encabezado_pedido_eliminado as ep "
                + "inner join detalle_pedido_eliminado as dp "
                + "on ep.id_pedeliminado = dp.encabezado_pedido_eliminado_id_pedeliminado\n"
                + "inner join producto as pr "
                + "on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us "
                + "on pr.usuario_id_usuario = us.id_usuario\n"
                + "where ep.usuario_id_usuario = " + id_user + "\n"
                + "group by ep.total, ep.id_pedeliminado, "
                + "dp.id_detalle_pedelimnado, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_pedeliminado asc";
        return conex.getRecordsInJson(sql);
    }

    public String listOrderStore(Order ord) {
        sql = "select distinct ep.id_encapedido,ep.fecha_venta, "
                + "ep.total, ep.descuento, ep.estado, ep.usuario_id_usuario\n"
                + "from encabezado_pedido as ep "
                + "inner join detalle_pedido as dp"
                + " on ep.id_encapedido = dp.encabezado_pedido_id_encapedido\n"
                + "inner join producto as pr "
                + "on dp.producto_id_producto = pr.id_producto \n"
                + "inner join usuario as us "
                + "on pr.usuario_id_usuario = us.id_usuario\n"
                + "where us.id_usuario = " + ord.getId_user() + "\n"
                + "group by ep.total, ep.id_encapedido, "
                + "dp.id_detpedido, pr.id_producto, us.id_usuario \n"
                + "order by ep.id_encapedido asc";
        System.out.println(sql);
        return conex.getRecordsInJson(sql);
    }

    public boolean cancelOrder(String id_order) {
        sql = "delete from detalle_pedido"
                + " where encabezado_pedido_id_encapedido = " + id_order + "";
        System.out.println(sql);
        if (conex.modifyBD(sql)) {
            sql = "delete from encabezado_pedido "
                    + "where id_encapedido = " + id_order + "";
        }
        return conex.modifyBD(sql);
    }

    public boolean dispatchOrder(String id_order) {
        sql = "update encabezado_pedido "
                + "set estado = 'd' where id_encapedido = " + id_order + "";
        System.out.println(sql);
        return conex.modifyBD(sql);
    }

}
