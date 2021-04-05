
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import java.util.LinkedList;
import java.util.Timer;

/**
 *
 * @author axelj
 */
public class HorariosDB {

   private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public HorariosDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public LinkedList<Horarios> ListarHorarios(String idCliente) throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Horarios> listaHorarios= new LinkedList<>();
        
        try{
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from horariosEntregaUsuario where idUsuario=" + "'" + idCliente + "' and estado= 1" ;
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String hora = rsPA.getString("horario");
                
                int idHorario = rsPA.getInt("idHorario");
                
                int estado = rsPA.getInt("estado");
                
                //se construye el objeto.
                Horarios horario = new Horarios(idHorario, estado, idUsuario, hora);
                
                listaHorarios.add(horario);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaHorarios;
    }
    
    public LinkedList<Horarios> ListarHorariosI(String idCliente) throws SNMPExceptions, SQLException{
        String select= "";
        LinkedList<Horarios> listaHorarios= new LinkedList<>();
        
        try{
            AccesoDatos accesoDatos= new AccesoDatos();
            //Se crea la sentencia de Busqueda
            select=
                    "SELECT * from horariosEntregaUsuario where idUsuario=" + "'" + idCliente + "' and estado= 2" ;
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String hora = rsPA.getString("horario");
                
                int idHorario = rsPA.getInt("idHorario");
                
                int estado = rsPA.getInt("estado");
                
                //se construye el objeto.
                Horarios hor = new Horarios(idHorario, estado, idUsuario, hora);
                
                listaHorarios.add(hor);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }finally{
            
        }
        return listaHorarios;
    }
    
    public void ModificarHorario(Horarios horarios) throws SNMPExceptions{
         String update = "";
         try{
             
             update = "update horariosEntregaUsuario set horario="+ "'" + horarios.horario + "'" + " where idHorario=" + horarios.idHorario;
             
             accesoDatos.ejecutaSQL(update);
             
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
    
    public void EliminarHorario(int idHorario) throws SNMPExceptions{
         String update = "";
          try{
              update = "update horariosEntregaUsuario set estado= 2 where idHorario= "+ idHorario ;
              
              accesoDatos.ejecutaSQL(update);
          }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
    
    public void ActivarHorario(int idHorario) throws SNMPExceptions{
         String update = "";
          try{
              update = "update horariosEntregaUsuario set estado= 1 where idHorario= "+ idHorario ;
              
              accesoDatos.ejecutaSQL(update);
          }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
    
    public void AgregarHorario(Horarios horario) throws SNMPExceptions{
         String insert = "";
         try{
             insert = "insert into horariosEntregaUsuario (idUsuario, horario, estado) values ('" + horario.idUsuario + "'," 
                     +"'" + horario.horario + "'," + horario.estado +")";
             
             accesoDatos.ejecutaSQL(insert);
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
     public Horarios HorarioModi(int idHorario) throws SNMPExceptions{
         String select = "";
         Horarios hor = null;
         try{
             select = "SELECT * from horariosEntregaUsuario where idHorario=" + idHorario ;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                
                String idUsuario = rsPA.getString("idUsuario");
                
                String hora = rsPA.getString("horario");
                
                int idHorario1 = rsPA.getInt("idHorario");
                
                int estado = rsPA.getInt("estado");
                
                //se construye el objeto.
                hor = new Horarios(idHorario1, estado, idUsuario, hora);
            }
            rsPA.close();//se cierra el ResultSeat.
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return hor;
     }
    
   
}
