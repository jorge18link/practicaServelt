
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelos.Conferencia;


@WebServlet(name = "ConferenciasServlet", urlPatterns = {"/Conferencia"})
public class ConferenciasServlet extends HttpServlet {

    @Resource(name = "conferencias")
    private DataSource conferencias;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("conferenciasArray", Conferencia.conferencias());
        
        String action = request.getParameter("action");
        
        System.out.println(action);
        
        if ("agregar".equals(action)){
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String fecha = request.getParameter("fecha");
            Conferencia.insertar(nombre, descripcion, fecha);
        }
        if ("editar".equals(action)){
            String fecha =request.getParameter("fecha");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println(fecha+" "+nombre+" "+descripcion+" "+id);
            
            Conferencia.editar(id,nombre, descripcion,fecha);
        }
        if ("eliminar".equals(action)){
            //
            String fecha =request.getParameter("fecha");
            String nombre = request.getParameter("nombre");
           String descripcion = request.getParameter("descripcion");
            int id = Integer.parseInt(request.getParameter("id"));
            
            Conferencia.eliminar(id);
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
        request.getRequestDispatcher("home.jsp")
                .forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ConferenciasServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConferenciasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ConferenciasServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConferenciasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
