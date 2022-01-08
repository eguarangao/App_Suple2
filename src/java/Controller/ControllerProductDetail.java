/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetailOrderDao;
import Models.DetailOrder;

/**
 *
 * @author churri
 */
public class ControllerProductDetail {

    DetailOrderDao detailOrderDao;
    String msj;

    public ControllerProductDetail() {
        this.msj = "";
    }

    /**
     * Method for insert an detail order in the database
     *
     *
     * @param id_product
     * @param quantity
     * @param price
     * @return Return a String.
     */
    public String insertDetailOrder(String id_product,
            String quantity, String price) {
        detailOrderDao = new DetailOrderDao();
        DetailOrder detailOrder = new DetailOrder();
        this.msj = "Error en los parametros ingresados";

        detailOrder.setQuantity(quantity);
        detailOrder.setPrice(price);
        detailOrder.setId_product(id_product);

        if (detailOrderDao.insertDetailOrder(detailOrder)) {
            this.msj = "Pedido realizado con exito";
        } else {
            this.msj = "Error de base de datos";
        }
        return this.msj;
    }

    /**
     * Method for decrease stock of the store exist in the database
     *
     *
     * @param idProduct
     * @param idQuantity
     * @return Return a String.
     */
    public String decreaseStock(String idProduct, String idQuantity) {
        detailOrderDao = new DetailOrderDao();
        DetailOrder detailOrder = new DetailOrder();
        this.msj = "Error en los parametros ingresados";

        detailOrder.setId_product(idProduct);
        detailOrder.setQuantity(idQuantity);

        if (detailOrderDao.decreaseStock(detailOrder)) {
            this.msj = "Stock actualizado correctamente";
        } else {
            this.msj = "Error de base de datos";
        }
        return this.msj;
    }

    /**
     * Method for increase stock of the store exist in the database
     *
     *
     * @param idProduct
     * @param idQuantity
     * @return Return a String.
     */
    public String increaseStock(String idProduct, String idQuantity) {
        detailOrderDao = new DetailOrderDao();
        DetailOrder detailOrder = new DetailOrder();
        this.msj = "Error en los parametros ingresados";

        detailOrder.setId_product(idProduct);
        detailOrder.setQuantity(idQuantity);

        if (detailOrderDao.increaseStock(detailOrder)) {
            this.msj = "Stock actualizado correctamente";
        } else {
            this.msj = "Error de base de datos";
        }
        return this.msj;
    }

}
