/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.OrderDao;
import Models.Order;

/**
 *
 * @author churri
 */
public class ControllerOrder {

//global variables order and message
    OrderDao orderDao;
    String msj;

    public ControllerOrder() {
        this.msj = "";
    }

    /**
     * Method for insert an user
     *
     *
     * @param condition
     * @param id_user
     * @param total
     * @return Return a String.
     */
    public String insertOrder(String condition, String id_user, String total) {
        orderDao = new OrderDao();
        Order pe = new Order();

        this.msj = "Error en los parametros de entrada";

        pe.setCondition(condition);
        pe.setId_user(id_user);
        pe.setTotal(total);

        //validation of the discount
        int discount = Integer.parseInt(orderDao.salesQuantityMonth(id_user));
        pe.setDiscount(discount == 0 ? discount
                : (discount > 0 && discount < 11) ? discount : 10);

        if (orderDao.insertOrder(pe)) {
            this.msj = "procesando...";
        } else {
            this.msj = "error de base de datos";
        }
        return this.msj;
    }

    /**
     * Method that lists the existing orders in the database
     *
     * @param id_user
     * @return Return a String.
     */
    public String listOrder(String id_user) {
        orderDao = new OrderDao();
        return orderDao.listOrder(id_user);
    }

    /**
     * method that lists existing canceled orders in the database
     *
     * @param id_user
     * @return Return a String.
     */
    public String listCanceledOrders(String id_user) {
        orderDao = new OrderDao();
        return orderDao.listOrderCancel(id_user);
    }

    /**
     * method that lists the existing store orders in the database
     *
     * @param id_user
     * @return Return a String.
     */
    public String listStoreOrder(String id_user) {
        orderDao = new OrderDao();
        Order ord = new Order();

        ord.setId_user(id_user);
        return orderDao.listOrderStore(ord);
    }

    /**
     * method that cancels orders
     *
     * @param id_order
     * @return Return a String.
     */
    public String canceledOrders(String id_order) {
        orderDao = new OrderDao();
        if (orderDao.cancelOrder(id_order)) {
            this.msj = "Pedido cancelado correctamente";
        } else {
            this.msj = "error";
        }
        return this.msj;
    }

    /**
     * Method for dispatching orders
     *
     * @param id_order
     * @return Return a String.
     */
    public String dispatchOrder(String id_order) {
        orderDao = new OrderDao();
        if (orderDao.dispatchOrder(id_order)) {
            this.msj = "Pedido despachado correctamente";
        } else {
            this.msj = "error";
        }
        return this.msj;
    }

    /**
     * Method to apply a discount amount
     *
     * @param id_user
     * @return Return a String
     */
    public String discountAmount(String id_user) {
        orderDao = new OrderDao();
        int discount = Integer.parseInt(orderDao.salesQuantityMonth(id_user));
        int discount_response = (discount == 0 ? discount
                : (discount > 0 && discount < 11) ? discount : 10);
        return String.valueOf(discount_response);
    }

}
