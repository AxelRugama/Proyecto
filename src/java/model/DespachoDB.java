/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DespachoDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
     public DespachoDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
     
      public LinkedList<Model.Factura> ListFacturas() throws SNMPExceptions{
        LinkedList<Model.Factura> lista = new LinkedList<>();
        String select = "";
        try{
            select = "select * from factura where estado = 1";
             
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
                
          Model.Factura fac = new Model.Factura(idFac, idPedido, idUsuario, descuento, IVA,precioTotal, fecha, tipoPago, estado);
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
      
       public LinkedList<Model.Factura> ListFacCliente(String idUsua) throws SNMPExceptions{
        LinkedList<Model.Factura> lista = new LinkedList<>();
        String select = "";
        try{
            select = "select * from factura where estado = 1 and idUsuario=" + "'" + idUsua + "'";
             
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
                
          Model.Factura fac = new Model.Factura(idFac, idPedido, idUsuario, descuento, IVA,precioTotal, fecha, tipoPago, estado);
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
       
       public void AgregarDespacho(Despacho despacho) throws SNMPExceptions{
            String insert = "";
         try{
             insert = "insert into despacho (idFactura, fecha, idUsuario) values (" + despacho.idFactura + "," 
                     +"'" + despacho.fecha + "','" + despacho.idUsuario +"')";
             
             accesoDatos.ejecutaSQL(insert);
         }catch(SQLException e){
            throw new SNMPExceptions (SNMPExceptions.SQL_EXCEPTION,
                                     e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,e.getMessage());
        }
       }
}
