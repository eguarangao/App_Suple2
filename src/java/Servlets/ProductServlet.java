/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controller.ControllerProduct;
import Models.UserSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dúval Carvajal S.
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductServlet extends HttpServlet {

    private UserSession usr = null;
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
        ControllerProduct pcon = new ControllerProduct();
        switch (this.opcion) {
            case "listarProductos":
                usr = (UserSession) request.getSession().getAttribute("usuario");
                response_server = pcon.listProduct(usr.getId_user());
                break;
            case "listarProductosTienda":
                String id_usuario = request.getParameter("id_usuario");
                response_server = pcon.listProductStore(id_usuario);
                break;
            case "insertarProducto":
                usr = (UserSession) request.getSession().getAttribute("usuario");
                String nombre = request.getParameter("nombre");
                String stock = request.getParameter("stock");
                String precio = request.getParameter("precio");
                System.out.println(nombre);
                if (usr != null) {
                    this.response_server = "{\"message\":\"" + pcon.insertProduct(nombre, stock, precio, usr.getId_user()) + "\"}";
                } else {
                    response_server = "no tienes sesion";
                }

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
