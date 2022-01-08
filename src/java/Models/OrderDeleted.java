/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Model of OrderDeleted, which has the necessary data for its implementation and data
 * handling in its respective table in the database
 *
 * @author churri
 */
public class OrderDeleted {

    private String id_order_deleted = "";
    private String id_user = "";
    private String date_deleted = "";
    private String date_order = "";
    private String discount = "";
    private String total = "";

    public OrderDeleted() {
    }

    public String getId_order_deleted() {
        return id_order_deleted;
    }

    public void setId_order_deleted(String id_order_deleted) {
        this.id_order_deleted = id_order_deleted;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getDate_deleted() {
        return date_deleted;
    }

    public void setDate_deleted(String date_deleted) {
        this.date_deleted = date_deleted;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
