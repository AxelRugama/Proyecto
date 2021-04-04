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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
  
import javax.sql.DataSource; 
/**
 *
 * @author Alberto
 */
public class ProvinciaDB {
    
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;  
    // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina
    private LinkedList<Provincia> listaP = new LinkedList<Provincia>();

    
    public ProvinciaDB (Connection conn) {
        accesoDatos = new AccesoDatos();    
        accesoDatos.setDbConn(conn);
    }
    
    public ProvinciaDB() {
        super();
    }
    
    
    public  LinkedList<Provincia> moTodo() throws SNMPExceptions, SQLException {
        
        // breakpoint en plublic**
        
      String select = "";
      LinkedList<Provincia> listaPro = new LinkedList<Provincia>();
          
          try {
    
              //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  
         
              select = "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,DSC_PROVINCIA,LOG_ACTIVO "
                        + "FROM PROVINCIAS WHERE LOG_ACTIVO=1;";
              
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
              
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString("" + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo= rsPA.getFloat("LOG_ACTIVO");
                
                Provincia perProvincia = new Provincia(codigoProvincia, dscCortaProvincia, dscProvincia,logActivo);
                listaPro.add(perProvincia);
                
              }
              
              rsPA.close(); // cierra conexion
              
          } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          return listaPro;
      }
    
  
    
    
}
