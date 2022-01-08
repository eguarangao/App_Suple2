/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.ControllerUser;
import Models.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author churri
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    private UserSession usr = null;
    private String opcion = "-1";
    private String response_server = "{}";

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
        opcion = request.getParameter("opcion");
        switch (opcion) {
            case "log":
                String usuario = request.getParameter("user");
                String clave = request.getParameter("pass");
                ControllerUser conemplo = new ControllerUser();
                usr = conemplo.login(usuario, clave);
                if (usr != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usr);
                    if (usr.getCondition().equals("a")) {
                        if (usr.getMarket_stall().equals("tienda")) {
                            response_server = "{\"redirect\":\"tienda.html\"}";
                        } else if (usr.getMarket_stall().equals("cliente")) {
                            response_server = "{\"redirect\":\"cliente.html\"}";
                        } else {
                            response_server = "{\"redirect\":\"admin.html\"}";
                        }
                    } else {
                        response_server = "{\"redirect\":\"index.html\"}";
                    }
                } else {
                    response_server = "{\"redirect\":\"index.html\"}";
                }
                break;
            case "ses":
                usr = (UserSession) request.getSession().getAttribute("usuario");
                if (usr != null) {
                    String nombre = String.format("%s %s", usr.getName().trim(), usr.getSurname().trim());
                    String infoUser = String.format("{\"getNombres\":\"%s\",\"getNombreUsuario\":\"%s\",\"getCargo\":\"%s\"}", nombre, usr.getName_user(), usr.getMarket_stall());
                    if (usr.getMarket_stall().equals("tienda")) {
                        response_server = "{\"redirect\":\"tienda.html\",\"userObject\":" + infoUser + "}";
                    } else if (usr.getMarket_stall().equals("cliente")) {
                        response_server = "{\"redirect\":\"cliente.html\",\"userObject\":" + infoUser + "}";
                    } else {
                        response_server = "{\"redirect\":\"admin.html\",\"userObject\":" + infoUser + "}";
                    }
                } else {
                    response_server = "{\"redirect\":\"index.html\"}";
                }
                break;
            default: //cerrar sesion
                request.getSession().setAttribute("usuario", null);
                response_server = "{\"redirect\":\"index.html\",\"permiso\":\"nothing\"}";
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
