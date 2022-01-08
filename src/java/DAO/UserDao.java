/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DataStatic.Conection;
import Models.User;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author churri
 */
public class UserDao {

    Conection conex;
    String sql = "";

    public UserDao() {
        conex = new Conection();
    }

    public boolean insertUser(User us) {
        sql = String.format("insert into usuario "
                + "(nombres, apellidos, nombre_tienda, estado, tipo_usuario,"
                + "usuario, contrasenia) "
                + "values('%s','%s','%s','%s','%s','%s','%s')",
                us.getNames(),
                us.getSurnames(), us.getName_store(),
                us.getCondition(), us.getType_user(),
                us.getUser(), us.getPassword());
        return conex.modifyBD(sql);
    }

    public boolean enable(User us) {
        sql = "update usuario set estado = 'a' "
                + "where id_usuario = " + us.getId_user() + "";
        return conex.modifyBD(sql);
    }

    public DefaultTableModel login(String name_user, String password) {
        sql = "select * from usuario "
                + "where usuario = '" + name_user
                + "'and contrasenia = '" + password + "'";
        System.out.println(sql);
        return conex.returnRecord(sql);
    }

    public String listUser() {
        sql = "select * from usuario order by id_usuario";
        return conex.getRecordsInJson(sql);
    }

}
