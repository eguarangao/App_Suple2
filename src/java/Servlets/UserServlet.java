/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.ControllerUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author churri
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UserServlet extends HttpServlet {
    
    private String response_server = "{}";
    private String opcion = "-1";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(response_server);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        this.opcion = request.getParameter("opcion");
        ControllerUser uscon = new ControllerUser();
        switch(this.opcion){
            case "datosUsuarios":
                    response_server = uscon.listUser();
                break;
            case "habilitarUsuario":
                String id_usuario = request.getParameter("id_usuario");
                    response_server = uscon.enableUser(id_usuario);
                break;
            case "insertarusuario":
                String nombres = request.getParameter("nombres");
                String apellidos = request.getParameter("apellidos");
                String nombre_tienda = request.getParameter("nombre_tienda");
                String nombre_user = request.getParameter("usuario");
                String contrasenia = request.getParameter("contrasenia");
                String tipo_usuario = request.getParameter("tipo_usuario");
                
                System.out.println(nombre_user +" "+ nombre_tienda);
                
                String estado = "d";
                
                this.response_server = "{\"message\":\""+uscon.insertUser(nombres, apellidos, nombre_tienda, estado, tipo_usuario, nombre_user, contrasenia)+"\"}";
                
                break;
        }
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
