/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Order detail model, which has the necessary data for its implementation and
 * data management in its respective table in the database
 *
 * @author churri
 */
public class DetailOrder {

    private String id_detail_order = "";
    private String id_order = "";
    private String id_user = "";
    private String id_product = "";
    private String price = "";
    private String quantity = "";

    public DetailOrder() {
    }

    public String getId_detail_order() {
        return id_detail_order;
    }

    public void setId_detail_order(String id_detail_order) {
        this.id_detail_order = id_detail_order;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

}
