/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProductDao;
import Models.Product;

/**
 *
 * @author churri
 */
public class ControllerProduct {

    ProductDao productDao;
    String msj;

    public ControllerProduct() {
        this.msj = "";
    }

    /**
     * Method for insert an product
     *
     * @param name
     * @param stock
     * @param price_unit
     * @param id_user
     * @return Return a String.
     */
    public String insertProduct(String name, String stock,
            String price_unit, String id_user) {
        productDao = new ProductDao();
        Product pd = new Product();
        this.msj = "Error en los parametros ingresados";
        pd.setName(name);
        pd.setQuantity(stock);
        pd.setPrice(price_unit);
        pd.setId_user(id_user);
        if (productDao.insertProduct(pd)) {
            this.msj = "Producto agregado correctamente";
        } else {
            this.msj = "Error al agregar producto.";
        }
        return this.msj;
    }
   /**
     * Method that lists the existing product in the database
     *
     * @param id_user
     * @return Return a String.
     */
    public String listProduct(String id_user) {
        productDao = new ProductDao();
        Product pd = new Product();
        pd.setId_user(id_user);
        return productDao.listProduct(pd);
    }
 /**
     * Method that lists the existing product in the store of the database
     *
     * @param id_user
     * @return Return a String.
     */
    public String listProductStore(String id_user) {
        productDao = new ProductDao();
        Product pd = new Product();
        pd.setId_user(id_user);
        return productDao.listProductStore(pd);
    }

}
