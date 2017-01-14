
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Prueba con la base de datos
        try {
            Connection con = conferencias.getConnection();
            PreparedStatement query= con.prepareStatement("insert into conferencia (nombre,descripcion,fecha) values (?,?,?)");
            query.setString(1, "netbeans");
            query.setString(2, "prueba");
            query.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            query.executeUpdate();
            
            System.out.println("ya inserte en la tabla");
            
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConferenciasServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        String nombre = request.getParameter("inputNombre");
        String fecha = request.getParameter("inputFecha");
        String desc = request.getParameter("comment");
        System.out.println(action);
        System.out.println(nombre + fecha + desc);
        if ("agregar".equals(action)){
            
        }
        if ("editar".equals(action)){
            //
        }
        if ("eliminar".equals(action)){
            //
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
