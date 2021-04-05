/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author axelj
 */
public class ProductoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
    public ProductoDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
     public LinkedList<Producto> ListarProductos() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Producto> listaProductos= new LinkedList<>();
        
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from producto";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int idProducto = rsPA.getInt("idProducto");
                
                String descripcion = rsPA.getString("descripcion");
                
                String imagen = rsPA.getString("fotografia");
                
                double precio = rsPA.getDouble("precio");
                String rol_s = "Cliente";
                
                int cantidadMinima = rsPA.getInt("cantidadMinima");
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                Producto producto = new Producto(idProducto, descripcion, imagen, precio, cantidadMinima, estado_s);
                
                listaProductos.add(producto);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaProductos;
    }
     
     public LinkedList<Producto> ListarProductosActivos() throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Producto> listaProductos= new LinkedList<>();
        
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from producto where estado= 1";
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int idProducto = rsPA.getInt("idProducto");
                
                String descripcion = rsPA.getString("descripcion");
                
                String imagen = rsPA.getString("fotografia");
                
                double precio = rsPA.getDouble("precio");
                String rol_s = "Cliente";
                
                int cantidadMinima = rsPA.getInt("cantidadMinima");
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                Producto producto = new Producto(idProducto, descripcion, imagen, precio, cantidadMinima, estado_s);
                
                listaProductos.add(producto);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaProductos;
    }
     
    public Producto productoModi(int idProducto) throws SNMPExceptions, SQLException{
        String select= "";
        Producto producto = null;
        try{
             
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select= "SELECT idProducto, descripcion, cantidadMinima, precio, fotografia, estado from producto where idProducto=" + idProducto;
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                int idProduct = rsPA.getInt("idProducto");
                
                String descripcion = rsPA.getString("descripcion");
                
                int cantidadMinima = rsPA.getInt("cantidadMinima");
                
                double precio = rsPA.getInt("precio");
                
                String imagen = rsPA.getString("fotografia");
                
                int estado = rsPA.getInt("estado");
                String estado_s;
                if(estado == 1){
                    estado_s = "Activo";
                }else{
                    estado_s = "Inactivo";
                }
                
                //se construye el objeto.
                producto = new Producto(idProduct, descripcion, imagen, precio, cantidadMinima, estado_s);
                
                
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(SNMPExceptions | ClassNotFoundException | NamingException e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }  
        return producto;
    }
    
    public void ModificarProducto(Producto p) 
            throws SNMPExceptions, SQLException {
        
    String update = "";
         try{
             Producto producto = new Producto();
             producto = p;
             int estado = 0;
             
             if(producto.estado_s.equals("Activo")){
                 estado = 1;
             }else{
                 estado = 2;
             }

        update = "update producto set descripcion=" + "'" + p.descripcion + "'" + " where idProducto=" + p.idProducto +
                 "update producto set fotografia=" + "'" + p.Imagen + "'" + " where idProducto=" + p.idProducto +
                 "update producto set precio=" + p.precio + " where idProducto=" + p.idProducto +
                 "update producto set cantidadMinima=" + p.cantidadMinima + " where idProducto=" + p.idProducto +
                "update producto set estado="  + estado + " where idProducto=" + p.idProducto ;
       
       accesoDatos.ejecutaSQL(update);
       
    } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
       } catch (SNMPExceptions | ClassNotFoundException | NamingException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public void eliminarProducto(int idProducto) 
            throws SNMPExceptions, SQLException {
        
            String update = "";
         try{
             Producto pro = new Producto();
             pro = productoModi(idProducto);
             int estado = 0;
             
             if(pro.estado_s.equals("Activo")){
                 estado = 2;
             }else{
                 estado = 2;
             }
             
         update ="update producto set estado=" + estado + "where idProducto=" + pro.idProducto;
       
       accesoDatos.ejecutaSQL(update);
       
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
    
    public void ingresarProducto(Producto p) 
            throws SNMPExceptions, SQLException {
        
    String insert = "";
         try{
       Producto producto = new Producto();
       producto = p;
       
       insert = "INSERT INTO producto (descripcion, fotografia, precio, cantidadMinima, estado) VALUES"
         + "(" + "'" + producto.descripcion + "'" + "," 
               + "'"+ producto.Imagen +"'"+ ","
               +  producto.precio + ","
               +  producto.cantidadMinima + ","
               +  producto.estado  + ")";
       
       accesoDatos.ejecutaSQL(insert);
    
       } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } 
    }
     
}
