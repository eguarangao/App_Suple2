/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDeletedDao;
import Models.OrderDeleted;

/**
 *
 * @author churri
 */
public class ControllerRemovedOrder {

    OrderDeletedDao orderDeletedDao;
    String msj;

    public ControllerRemovedOrder() {
        this.msj = "";
    }

    /**
     * Method for insert an Order deleted in the database
     *
     * @param date_order
     * @param id_user
     * @param discount
     * @param total
     * @return Return a String.
     */
    public String insertOrderDeleted(String date_order, String id_user,
            String discount, String total) {
        orderDeletedDao = new OrderDeletedDao();
        OrderDeleted od = new OrderDeleted();

        this.msj = "Error en los parametros ingresados";
        od.setDate_order(date_order);
        od.setId_user(id_user);
        od.setDiscount(discount);
        od.setTotal(total);

        if (orderDeletedDao.insertOrderDeleted(od)) {
            this.msj = "Corecto";
        } else {
            this.msj = "Error de base dedatos";
        }

        return this.msj;
    }

}
