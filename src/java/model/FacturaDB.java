
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
public class FacturaDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public FacturaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
     public void GuardarFactura(Factura fac) throws SNMPExceptions{
         String insert = "";
         try{
             insert = "insert into factura (idFactura,idPedido,idUsuario,descuento,impuestoIVA,precioTotal, fecha, tipoPago, estado)  "
                     + "values (" + fac.idFactura + "," + fac.idPedido + ",'" + fac.idUsuario + "'," + fac.descuento + "," + fac.impuestoIVA + "," + fac.precioTotal + "," 
                     + "'" + fac.fecha + "'," 
                     + "'" + fac.tipoPago + "'," 
                     + fac.estado +")";
             
             accesoDatos.ejecutaSQL(insert);
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
     
         public int RetornaIdFactura() throws SNMPExceptions{
         String select = "";
         int idFactura = 0;
         try{
             select = "SELECT MAX(idFactura) as idMayorF from factura";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                idFactura = rsPA.getInt("idMayorF");
            }
            rsPA.close();//se cierra el ResultSeat.
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return idFactura;
     }
         
         public Factura RetornaFactura(int idFactura) throws SNMPExceptions{
         String select = "";
         Factura fac = null;
         try{
             select = "select * from factura where idFactura =" + idFactura;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                int idFac = rsPA.getInt("idFactura");
                int idPedido = rsPA.getInt("idPedido");
                String idUsuario = rsPA.getString("idUsuario");
                double descuento = rsPA.getDouble("descuento");
                double IVA = rsPA.getDouble("impuestoIVA");
                double precioTotal = rsPA.getDouble("precioTotal");
                String fecha = rsPA.getString("fecha");
                String tipoPago = rsPA.getString("tipoPago");
                int estado = rsPA.getInt("estado");
                
          fac= new Factura(idFac, idPedido, idUsuario, descuento, IVA,precioTotal, fecha, tipoPago, estado);
            }
            rsPA.close();//se cierra el ResultSeat.
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return fac;
     }
         
         public LinkedList<Factura> ListFac(int idFactura) throws SNMPExceptions{
        LinkedList<Factura> lista = new LinkedList<>();
        String select = "";
        try{
            select = "select * from factura where idFactura =" + idFactura;
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
                int idFac = rsPA.getInt("idFactura");
                int idPedido = rsPA.getInt("idPedido");
                String idUsuario = rsPA.getString("idUsuario");
                double descuento = rsPA.getDouble("descuento");
                double IVA = rsPA.getDouble("impuestoIVA");
                double precioTotal = rsPA.getDouble("precioTotal");
                String fecha = rsPA.getString("fecha");
                String tipoPago = rsPA.getString("tipoPago");
                int estado = rsPA.getInt("estado");
                
          Factura fac = new Factura(idFac, idPedido, idUsuario, descuento, IVA,precioTotal, fecha, tipoPago, estado);
          lista.add(fac);
            }
            rsPA.close();//se cierra el ResultSeat.
            
        }catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return lista;
    }

          public void ModificarFactura(int idFactura) throws SNMPExceptions{
         String update = "";
         try{
             update = "update factura set estado = 0 where idFactura =" + idFactura;
             
             accesoDatos.ejecutaSQL(update);
             
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
     }
          
          public int Pendientes() throws SNMPExceptions{
         String select = "";
         int pendientes= 0;
         try{
             select = "select COUNT(*) as pendientes from factura where estado = 1";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               pendientes = rsPA.getInt("pendientes");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return pendientes;
     }
          
          public int Entregados() throws SNMPExceptions{
         String select = "";
         int entregados = 0;
         try{
             select = "select COUNT(*) as entregados from factura where estado = 0";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               entregados = rsPA.getInt("entregados");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return entregados;
     }
          
          public int Contado() throws SNMPExceptions{
         String select = "";
         int contado = 0;
         try{
             select = "select COUNT(*) as contado from factura where tipoPago = 'Efectivo'";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               contado = rsPA.getInt("contado");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return contado;
     }
          
          public int CuentaPorCobrar() throws SNMPExceptions{
         String select = "";
         int cuentaCobrar = 0;
         try{
             select = "select COUNT(*) as cuentaCobrar from factura where tipoPago = 'Cuenta Por Cobrar'";
             
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            while(rsPA.next()){
               cuentaCobrar = rsPA.getInt("cuentaCobrar");
            }
            rsPA.close();
            
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
         return cuentaCobrar;
     }
     
     
}
