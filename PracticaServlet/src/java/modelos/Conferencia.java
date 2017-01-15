package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Conferencia {
    
    int id;
    String nombre;
    String descripcion;
    Date fecha;
   
    public Conferencia(int id,String nombre, String descripcion, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public static boolean insertar(String nombre, String descripcion, String fecha) throws SQLException{
        
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            
            PreparedStatement query= con.prepareStatement("insert into conferencia (nombre,descripcion,fecha) values (?,?,?)");
            query.setString(1, nombre);
            query.setString(2, descripcion);
            query.setDate(3, java.sql.Date.valueOf(fecha));
            query.executeUpdate();
            con.close();
            return true;
            
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    public static ArrayList<Conferencia> conferencias() throws SQLException, ParseException{
        //codigo de lecturas y generacion de arraylist de conferencias
        ArrayList<Conferencia> conferencias= new ArrayList();
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            PreparedStatement query= con.prepareStatement("select * from conferencia");
            
            ResultSet rs = query.executeQuery();
            //Se llena el array
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = rs.getString("fecha");
                Date fecha;
                
                fecha = formatoDelTexto.parse(strFecha);
                
                conferencias.add(new Conferencia(id,nombre,descripcion,fecha));   
            }
            
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conferencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private static DataSource getDbConferencias() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/dbConferencias");
    }

}
