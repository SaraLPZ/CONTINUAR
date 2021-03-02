/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sara;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Alexara
 */
public class AccesoBD {    

    public static Usuario logearUsuario(String nombre, String password) {
        try {
            //"jdbc:mysql://localhost:3306/tienda", "root", ""
            //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sara", "root", "");
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            String sql="SELECT id, nombre from Usuarios where nombre='"+nombre+"' AND password='"+password+"'";
            //SELECT * FROM Usuarios WHERE `nombre`="sara" AND `password`="sara"
            Statement stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next()){
                String nombre_bd=rs.getString("nombre");
                int id_bd=rs.getInt("id");
                Usuario u=new Usuario(id_bd, nombre);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    static ArrayList<Articulo> recuperarArticulos(String nombre_buscado) {
        ArrayList<Articulo> lista=new ArrayList<>();
        try {
            
            String query="SELECT * FROM Articulos";
            if (nombre_buscado!="")
            {
                query +=" WHERE CodigoArt='"+nombre_buscado+"'";
            }
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            Statement stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                String CodigoArt=rs.getString("CodigoArt");
                String DescrArt=rs.getString("DescrArt");
                String PrecioArt=rs.getString("PrecioArt");
                Articulo a=new Articulo(CodigoArt, DescrArt, PrecioArt);
                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    
    static ArrayList<Cliente> recuperarClientes(String nombre_buscado) {
        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            
            String query="SELECT * FROM Clientes";
            if (nombre_buscado!="")
            {
                query +=" WHERE NIFCliente='"+nombre_buscado+"'";
            }
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            Statement stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                
                String NIFCliente=rs.getString("NIFCliente");
                String DomicilioCliente=rs.getString("DomicilioCliente");
                String NombreCliente=rs.getString("NombreCliente");
                Cliente c=new Cliente(NIFCliente, DomicilioCliente, NombreCliente);
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    static void actualizarProductos(String codigo_unico, String nueva_descripcion, String nuevo_precio) {
        try {
            //UPDATE Articulos SET `CodigoArt`="1a", `DescrArt`="Raton", `PrecioArt`="40"
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            Statement stmt=conexion.createStatement();
            String sql="UPDATE Articulos SET DescrArt='"+nueva_descripcion+"', PrecioArt='"+nuevo_precio+"' WHERE CodigoArt='"+codigo_unico+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void actualizarClientes(String nif_unico, String nuevo_domicilio, String nuevo_nombre) {
        try {
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            Statement stmt=conexion.createStatement();
            //UPDATE `Clientes` SET `DomicilioCliente`="Calle Tulipan",`NombreCliente`="Martin" WHERE `NIFCliente`="333333333J"
            String sql="UPDATE Clientes SET DomicilioCliente='"+nuevo_domicilio+"', NombreCliente='"+nuevo_nombre+"' WHERE NIFCliente='"+nif_unico+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void insertarCliente(String nuevo_nif, String nuevo_domicilio, String nuevo_nombre) {
        try {
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/RBZ856FQZu","RBZ856FQZu","WHyUN6pd20");
            Statement stmt=conexion.createStatement();
            //INSERT INTO `Clientes`(`NIFCliente`, `DomicilioCliente`, `NombreCliente`) VALUES ("123456789P","Calle Sevillista","Patricia")
            String sql="INSERT INTO Clientes (NIFCliente,DomicilioCliente,NombreCliente) VALUES ('"+nuevo_nif+"','"+nuevo_domicilio+"','"+nuevo_nombre+"')";
            stmt.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
