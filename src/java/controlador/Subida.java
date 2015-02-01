/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Foto;
import hibernate.Inmueble;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.ModeloFoto;
import modelo.ModeloInmueble;

/**
 *
 * @author rober
 */
@WebServlet(name = "Subida", urlPatterns = {"/subida"})
@MultipartConfig
public class Subida extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
         
        if (target.equals("foto")
                && op.equals("delete")
                && action.equals("op")) {
            forward = false;
            String id = request.getParameter("id");
            Foto f = ModeloFoto.get(id);
            ModeloFoto.delete(id);
            destino = "subida?target=fotos&op=insert&action=view&id=" + f.getInmueble().getId();
        } else if(target.equals("foto")
                && op.equals("insert")
                && action.equals("view")){
            forward = true;
//            request.setAttribute("id", request.getParameter("id"));
            Inmueble i = ModeloInmueble.get(request.getParameter("id"));
            request.setAttribute("datos", ModeloFoto.get(i));
            request.setAttribute("inmueble", i);
            destino = "WEB-INF/inmueble/foto.jsp";
        } else if(target.equals("foto")
                && op.equals("insert")
                && action.equals("op")){
            forward = false;
            String id = request.getParameter("id");
            destino = "control?target=inmueble&op=select&action=view";
            System.out.println("aaaaaaaaaaaaa fotos insert op" );
            Part filePart = request.getPart("file"); 
            InputStream fileContent = filePart.getInputStream();
            String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "img/Inmueble_"+fecha+"_"+id+".jpg";
            response.setContentType("application/json;charset=UTF-8");
            try {
                System.out.println("aaaaaaaaaaaa estoy en el try");
                FileOutputStream fos = new FileOutputStream( getServletContext().getRealPath("/") + fileName);
                System.out.println("aaaaaaaaaaaa " + fileName);
                byte[] array = new byte[1000]; // buffer temporal de lectura.
                int leido = fileContent.read(array);
                while (leido > 0) {
                    fos.write(array, 0, leido);
                    leido = fileContent.read(array);
                }
                // cierre de conexion y fichero.
                fileContent.close();
                fos.close();
                Foto f = new Foto();
                f.setInmueble(ModeloInmueble.get(id));
                f.setRuta(fileName);
                ModeloFoto.insert(f);
            } catch (Exception e) {
                System.out.println("AAAAAAAAAAAAAAAAAA: "+e.toString());
            }

            /*try(PrintWriter out = response.getWriter()){
                //out.println("<img src='subido/"+description+".jpg'/>");
                if(error)
                    out.print("(\"r\":0)");
                else
                    out.print("(\"r\":1)");
            }*/
        }
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        int id =(int) request.getAttribute("id");
//        System.out.println("dopost id "+id);
//        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
//        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String fileName = "Inmueble_"+fecha+"_"+id;
//        InputStream fileContent = filePart.getInputStream();
//        
//        String destino = getServletContext().getRealPath("/")+"img/";
//        try {
//            FileOutputStream fos = new FileOutputStream(destino+fileName);
//            // Lectura de la foto de la web y escritura en fichero local
//            byte[] array = new byte[1000]; // buffer temporal de lectura.
//            int leido = fileContent.read(array);
//            while (leido > 0) {
//                fos.write(array, 0, leido);
//                leido = fileContent.read(array);
//            }
//            fileContent.close();
//            fos.close();
//            
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            try (PrintWriter out = response.getWriter()) {
//                out.println("{\"r\":0}");
//            }
//        }
//        try (PrintWriter out = response.getWriter()) {
//            out.println("{\"r\":1}");
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
