/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Model DetailOrderDeleted, which has the necessary data for its
 * implementation and data handling in its respective table in the database
 *
 * @author churri
 */
public class DetailOrderDeleted {

    private String id_detail_order_deleted = "";
    private String id_order_deleted = "";
    private String id_product = "";
    private String quantity = "";
    private String price = "";

    public DetailOrderDeleted() {
    }

    public String getId_detail_order_deleted() {
        return id_detail_order_deleted;
    }

    public void setId_detail_order_deleted(String id_detail_order_deleted) {
        this.id_detail_order_deleted = id_detail_order_deleted;
    }

    public String getId_order_deleted() {
        return id_order_deleted;
    }

    public void setId_order_deleted(String id_order_deleted) {
        this.id_order_deleted = id_order_deleted;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
