/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.Product;

/**
 *
 * @author churri
 */
public class ProductDao {

    Conection conex;
    String sql = "";

    public ProductDao() {
        conex = new Conection();
    }

    public boolean insertProduct(Product pc) {
        sql = String.format("insert into producto"
                + "(nombre, stock, precio_unit, usuario_id_usuario) "
                + "values('%s', %s, %s, %s)",
                pc.getName(), pc.getQuantity(),
                pc.getPrice(), pc.getId_user());
        return conex.modifyBD(sql);
    }

    public String listProduct(Product pc) {
        sql = "select * from producto "
                + "where usuario_id_usuario = " + pc.getId_user() + " "
                + "and stock > 0 order by id_producto asc";
        return conex.getRecordsInJson(sql);
    }

    public String listProductStore(Product pc) {
        sql = "select * from producto "
                + "where usuario_id_usuario = " + pc.getId_user() + " "
                + "and stock > 0 order by id_producto asc";
        return conex.getRecordsInJson(sql);
    }

}
