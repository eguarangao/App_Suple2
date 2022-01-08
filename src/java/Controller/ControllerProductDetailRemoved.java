/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DetailOrderDeletedDao;
import Models.DetailOrderDeleted;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class ControllerProductDetailRemoved {

    DetailOrderDeletedDao detailOrderDeletedDao;
    String message;

    public ControllerProductDetailRemoved() {
        this.message = "";
    }

    /**
     * Method for insert an detail order deleted in the database sending the
     * corresponding parameters
     *
     * @param quantity
     * @param price
     * @param id_product
     * @return Return a String.
     */
    public String insertDetailOrderDeleted(String quantity,
            String price, String id_product) {
        detailOrderDeletedDao = new DetailOrderDeletedDao();
        DetailOrderDeleted detOrdDel = new DetailOrderDeleted();
        this.message = "Error en los parametros ingresados";

        detOrdDel.setQuantity(quantity);
        detOrdDel.setPrice(price);
        detOrdDel.setId_product(id_product);

        if (detailOrderDeletedDao.insertDetailOrderDeleted(detOrdDel)) {
            this.message = "correcto";
        } else {
            this.message = "error en la base de datos";
        }

        return this.message;
    }

    /**
     *
     * method to obtain a specific product
     *
     * @param id_order
     * @return return String
     */
    public String getProductos(String id_order) {
        detailOrderDeletedDao = new DetailOrderDeletedDao();
        DefaultTableModel table = detailOrderDeletedDao.getProductos(id_order);
        String datail = "";
        for (int i = 0; i < table.getRowCount(); i++) {
            datail += table.getValueAt(i, 4).toString() + ";"
                    + table.getValueAt(i, 1).toString()
                    + ";" + table.getValueAt(i, 3).toString();
            if (i < table.getRowCount() - 1) {
                datail += "/";
            }
        }
        System.out.println(datail);
        return datail;
    }

}
