/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Inmueble;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ModeloInmueble;

/**
 *
 * @author rober
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");

        if (target.equals("inmueble")
                && op.equals("select")
                && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/inmueble/index.jsp";
            request.setAttribute("datos", ModeloInmueble.get());
        } else if (target.equals("inmueble")
                && op.equals("delete")
                && action.equals("op")) {
            forward = false;
            ModeloInmueble.delete(request.getParameter("id"));
            destino = "control?target=inmueble&op=select&action=view";
        } else if (target.equals("inmueble")
                && op.equals("insert")
                && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/inmueble/insert.jsp";
        } else if (target.equals("inmueble")
                && op.equals("insert")
                && action.equals("op")) {
            System.out.println("bbbbbbbbbbbbbb");
            forward = false;
            destino = "control?target=inmueble&op=select&action=view";
            Inmueble inmueble = new Inmueble();
            inmueble.setTipo(request.getParameter("tipo"));
            inmueble.setDireccion(request.getParameter("direccion"));
            inmueble.setLocalidad(request.getParameter("localidad"));
            inmueble.setUsuario(request.getParameter("usuario"));
            inmueble.setPrecio(BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio"))));
            ModeloInmueble.insert(inmueble);
        } else if (target.equals("inmueble")
                && op.equals("edit")
                && action.equals("view")) {
            forward = true;
            //request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("inmueble", ModeloInmueble.get(request.getParameter("id")));
            System.out.println("hola");
            destino = "WEB-INF/inmueble/edit.jsp";
            System.out.println(destino);
        } else if (target.equals("inmueble")
                && op.equals("edit")
                && action.equals("op")) {
            forward = false;
            destino = "control?target=inmueble&op=select&action=view";
            Inmueble inmueble = new Inmueble();
            inmueble.setTipo(request.getParameter("tipo"));
            inmueble.setDireccion(request.getParameter("direccion"));
            inmueble.setLocalidad(request.getParameter("localidad"));
            inmueble.setUsuario(request.getParameter("usuario"));
            inmueble.setPrecio(BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio"))));
            inmueble.setId(Integer.parseInt(request.getParameter("id")));
            ModeloInmueble.edit(inmueble);
        }
//        } else if (target.equals("inmueble")
//                && op.equals("foto")
//                && action.equals("view")) {
//            forward = true;
//            request.setAttribute("id", request.getParameter("id"));
//            System.out.println("controlador: "+request.getParameter("id"));
//            destino = "WEB-INF/inmueble/foto.jsp";
//        }
        if (forward) {
            request.getRequestDispatcher(destino).forward(request, response);
        } else {
            response.sendRedirect(destino);
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
