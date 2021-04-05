/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author axelj
 */
public class DireccionesDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
    
    public DireccionesDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
     public LinkedList<Direcciones> ListarDirecciones(String idCliente) throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Direcciones> listaDirecciones= new LinkedList<>();
        
        try{
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from direccionEntregaUsuario where idUsuario=" + "'" + idCliente + "' and estado= 1" ;
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String direccion = rsPA.getString("direccion");
                
                int idDireccion = rsPA.getInt("idDireccion");
                
                int estado = rsPA.getInt("estado");
                
                String provincia = rsPA.getString("provincia");
                
                String distrito = rsPA.getString("distrito");
                
                String cantones = rsPA.getString("canton");
                
                String barrios = rsPA.getString("barrio");
                
//                se construye el objeto.
                Direcciones direcciones = new Direcciones(idUsuario, direccion,idDireccion, estado, provincia, distrito, cantones, barrios);
                
                listaDirecciones.add(direcciones);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaDirecciones;
    }
     
     public LinkedList<Direcciones> ListarDireccionesI(String idCliente) throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Direcciones> listaDirecciones= new LinkedList<>();
        
        try{
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from direccionEntregaUsuario where idUsuario=" + "'" + idCliente + "' and estado= 2" ;
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String direccion = rsPA.getString("direccion");
                
                int idDireccion = rsPA.getInt("idDireccion");
                
                int estado = rsPA.getInt("estado");
                
                String provincia = rsPA.getString("provincia");
                
                String distrito = rsPA.getString("distrito");
                
                String cantones = rsPA.getString("canton");
                
                String barrios = rsPA.getString("barrio");
                
                //se construye el objeto.
                Direcciones direcciones = new Direcciones(idUsuario, direccion,idDireccion, estado, provincia, distrito, cantones, barrios);
                
                listaDirecciones.add(direcciones);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaDirecciones;
    }
     
     public void ModificarDireccion(Direcciones direccion) throws SNMPExceptions{
         String update = "";
         try{
             
             update = "update direccionEntregaUsuario set direccion="+ "'" + direccion.direccion + "'" + " where idDireccion=" + direccion.idDireccion;
             
             accesoDatos.ejecutaSQL(update);
             
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
     public void EliminarDireccion(int idDireccion) throws SNMPExceptions{
         String update = "";
          try{
              update = "update direccionEntregaUsuario set estado= 2 where idDireccion= "+ idDireccion ;
              
              accesoDatos.ejecutaSQL(update);
          }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
      public void ActivarDireccion(int idDireccion) throws SNMPExceptions{
         String update = "";
          try{
              update = "update direccionEntregaUsuario set estado= 1 where idDireccion= "+ idDireccion ;
              
              accesoDatos.ejecutaSQL(update);
          }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
     public void AgregarDireccion(Direcciones direccion) throws SNMPExceptions{
         String insert = "";
         try{
             insert = "insert into direccionEntregaUsuario (idUsuario, direccion, estado) values ('" + direccion.idUsuario + "'," 
                     +"'" + direccion.direccion + "'," + direccion.estado +")";
             
             accesoDatos.ejecutaSQL(insert);
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
     public Direcciones DireccionModi(int idDireccion) throws SNMPExceptions{
         String select = "";
         Direcciones direc = null;
         try{
             select = "SELECT * from direccionEntregaUsuario where idDireccion=" + idDireccion ;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String direccion = rsPA.getString("direccion");
                
                int idDireccion1 = rsPA.getInt("idDireccion");
                
                int estado = rsPA.getInt("estado");
                
                String provincia = rsPA.getString("provincia");
                
                String distrito = rsPA.getString("distrito");
                
                String cantones = rsPA.getString("canton");
                
                String barrios = rsPA.getString("barrio");
                
                //se construye el objeto.
                direc = new Direcciones(idUsuario, direccion,idDireccion1, estado, provincia, distrito, cantones, barrios);   
            }
            rsPA.close();//se cierra el ResultSeat.
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return direc;
     }

     
}
