package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author axelj
 */
public class PedidosDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public PedidosDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void GuardarPedido(Pedidos ped) throws SNMPExceptions {
        String insert = "";
        try {
            insert = "insert into pedido (idPedido, idUsuario, idHorarioEntrega, idDireccionEntrega) "
                    + "values (" + ped.idPedido + ",'" + ped.idUsuario + "'," + ped.idHorarioEntrega + "," + ped.idDireccionEntrega + ")";

            accesoDatos.ejecutaSQL(insert);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
    
    public int RetornaIdPedido() throws SNMPExceptions{
         String select = "";
         int idPedido = 0;
         try{
             select = "SELECT MAX(idPedido) as idMayorP from pedido";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                idPedido = rsPA.getInt("idMayorP");
            }
            rsPA.close();//se cierra el ResultSeat.
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return idPedido;
     }
    
    public String RetornaDireccion(int idPedido) throws SNMPExceptions{
         String select = "";
         String direccion = "";
         try{
             select = "select direccion from direccionEntregaUsuario dr inner join pedido p "
                     + "on dr.idDireccion = p.idDireccionEntrega where p.idPedido =" + idPedido;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               direccion = rsPA.getString("direccion");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return direccion;
     }
    
     public String RetornaHorario(int idPedido) throws SNMPExceptions{
         String select = "";
         String horario = "";
         try{
             select = "select horario from horariosEntregaUsuario hr inner join pedido p "
                     + "on hr.idHorario = p.idHorarioEntrega where p.idPedido =" + idPedido;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               horario = rsPA.getString("horario");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return horario;
     }

}
