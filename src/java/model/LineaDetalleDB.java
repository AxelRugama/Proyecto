
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
public class LineaDetalleDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public LineaDetalleDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public void GuardarLineas(LineaDetalle linea) throws SNMPExceptions {
        
        String insert = "";
        try {
            insert = "insert into detalleFactura (idLinea, idFactura, idProducto, precioProducto, cantidadProducto) "
                    + "values (" + linea.idLinea + "," + linea.idFactura + "," + linea.idProducto + "," + linea.precioProducto + "," + linea.cantidadProducto + ")";

            accesoDatos.ejecutaSQL(insert);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
    
    public LinkedList<LineaDetalle> ListarLineas(int idFactura) throws SNMPExceptions{
        LinkedList<LineaDetalle> lista = new LinkedList<>();
        String select = "";
        try{
            select = "select df.idLinea, df.cantidadProducto, df.precioProducto, p.descripcion from detalleFactura df inner join producto p "
                    + "on df.idProducto = p.idProducto where df.idFactura =" + idFactura + "order by df.idLinea";
            
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            
            while(rsPA.next()){
                
                int idLinea = rsPA.getInt("idLinea");
                int cantidad = rsPA.getInt("cantidadProducto");
                double precio = rsPA.getDouble("precioProducto");
                String producto = rsPA.getString("descripcion");
               
                LineaDetalle linea = new LineaDetalle(idLinea, precio, cantidad, producto);
                lista.add(linea);
            }
            rsPA.close();
            
        }catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return lista;
    }
    
}
