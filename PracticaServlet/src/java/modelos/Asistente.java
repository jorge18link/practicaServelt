/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Jorge
 */
public class Asistente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String conferencia;
    private String correo;

    public Asistente(int id, String cedula, String nombre, String apellido, String conferencia, String correo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.conferencia = conferencia;
        this.correo = correo;
    }
    
    public static boolean insertar(String cedula, String nombre, String apellido,String conferencia,String correo) throws SQLException{
        
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            
            PreparedStatement query= con.prepareStatement("insert into asistente (cedula,nombre,apellido,conferencia,correo) values (?,?,?,?,?)");
            query.setString(1, cedula);
            query.setString(2, nombre);
            query.setString(3, apellido);
            query.setString(4, conferencia);
            query.setString(5, correo);
            query.executeUpdate();
            con.close();
            return true;
            
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static boolean editar(int id,String cedula, String nombre, String apellido,String conferencia,String correo) throws SQLException{
          
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            
            PreparedStatement query= con.prepareStatement("UPDATE asistente SET cedula=?,nombre=?,apellido=?,conferencia=?,correo=? WHERE id=?");
            
            query.setString(1, cedula);
            query.setString(2, nombre);
            query.setString(3, apellido);
            query.setString(4, conferencia);
            query.setString(5, correo);
            query.setInt(6, id);
            query.executeUpdate();
            con.close();
            System.out.println("Se Actualizado Exitosamente");
            return true;
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("no se actualizo");
        return false;
    }
    
    public static boolean eliminar(int id) throws SQLException{
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            
            PreparedStatement query= con.prepareStatement("DELETE FROM asistente WHERE id=?");
            query.setInt(1, id);
            query.executeUpdate();
            con.close();
            System.out.println("Se Elimino Exitosamente");
            return true;
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("no se ha eliminado");
        return false;
        
    }
    
    public static ArrayList<Asistente> asistentes() throws SQLException, ParseException{
        //codigo de lecturas y generacion de arraylist de conferencias
        ArrayList<Asistente> asistentes= new ArrayList();
        try {
            DataSource dbConferencias = getDbConferencias();
            Connection con = dbConferencias.getConnection();
            PreparedStatement query= con.prepareStatement("select * from asistente");
            
            ResultSet rs = query.executeQuery();
            //Se llena el array
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String cedula =rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String conferencia = rs.getString("conferencia");
                String correo = rs.getString("correo");
                
                asistentes.add(new Asistente(id,cedula,nombre,apellido,conferencia,correo));   
            }
            
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(Conferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asistentes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    private static DataSource getDbConferencias() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/dbConferencias");
    }
}
