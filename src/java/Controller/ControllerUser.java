/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDao;
import Models.UserSession;
import Models.User;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class ControllerUser {

    UserDao userDao;
    String message;

    public ControllerUser() {
        message = "";
    }

 /**
     * Method for insert an user in the database 
     *
     * @param name
     * @param last_name
     * @param name_store
     * @param condition
     * @param tipe_user
     * @param user
     * @param password
     * @return Return a String.
     */
    public String insertUser(String name, String last_name,
            String name_store, String condition,
            String tipe_user, String user, String password) {

        userDao = new UserDao();
        User us = new User();
        this.message = "Error en los parametros de entrada.";
        us.setNames(name);
        us.setSurnames(last_name);
        us.setName_store(name_store);
        us.setCondition(condition);
        us.setType_user(tipe_user);
        us.setUser(user);
        us.setPassword(password);
        if (userDao.insertUser(us)) {
            this.message = "Usuario registrado correctamente.";
        } else {
            this.message = "Erro al agregar un nuevo usuario.";
        }
        return this.message;
    }
 /**
     * Method for enable an user in the database    *
     * @param id_user
     * @return Return a String.
     */

    public String enableUser(String id_user) {
        userDao = new UserDao();
        User us = new User();
        this.message = "Error en los parametros de entrada.";
        us.setId_user(id_user);
        if (userDao.enable(us)) {
            this.message = "Usuario habilitado correctamente";
        } else {
            this.message = "Error al habilitar el usuario";
        }
        return this.message;
    }

    public UserSession login(String user, String password) {
        UserSession usr = null;
        userDao = new UserDao();
        DefaultTableModel employeeresponse = userDao.login(user, password);
        if (employeeresponse.getRowCount() > 0) {
            usr = new UserSession();
            usr.setId_user(
                    employeeresponse.getValueAt(0, 0).toString());
            usr.setName(
                    employeeresponse.getValueAt(0, 1).toString());
            usr.setSurname(
                    employeeresponse.getValueAt(0, 2).toString());
            usr.setName_user(
                    employeeresponse.getValueAt(0, 6).toString());
            usr.setMarket_stall(
                    employeeresponse.getValueAt(0, 5).toString());
            usr.setCondition(
                    employeeresponse.getValueAt(0, 4).toString());
        } else {
            usr = null;
        }
        return usr;
    }

    public String listUser() {
        userDao = new UserDao();
        return userDao.listUser();
    }

}
