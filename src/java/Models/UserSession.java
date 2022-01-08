/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Model of User Session, which has the necessary data for its implementation
 * and data handling in its respective table in the database
 *
 * @author Dúval Carvajal S.
 */
public class UserSession {

    private String id_user = "";
    private String name_user = "";
    private String market_stall = "";
    private String name = "";
    private String surname = "";
    private String condition = "";

    public UserSession() {
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getMarket_stall() {
        return market_stall;
    }

    public void setMarket_stall(String market_stall) {
        this.market_stall = market_stall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

}
