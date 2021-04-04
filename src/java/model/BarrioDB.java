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
public class BarrioDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    private LinkedList<Barrio> listaB = new LinkedList<Barrio>(); // manejar un arrelgo que se encarga de trasladar los registros de resultser a la pagina

    public BarrioDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public BarrioDB() {
        super();
    }

    public LinkedList<Barrio> SeleccionarBarrioporDistrito(float codigoProv,float codigoCanton, float codigoDistrito)
            throws SNMPExceptions, SQLException {
        
        String strSQL = "";
        LinkedList<Barrio> listBarrio = new LinkedList<Barrio>();

        try {

            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            strSQL = "SELECT COD_PROVINCIA,COD_CANTON,COD_DISTRITO,COD_BARRIO,DSC_BARRIO,LOG_ACTIVO\n " +
                     "FROM BARRIO\n " +
                     "WHERE LOG_ACTIVO=1 AND COD_PROVINCIA="+ codigoProv +"\n " +
                     "AND COD_CANTON="+ codigoCanton +" AND COD_DISTRITO="+ codigoDistrito +";";
                    
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            while (rsPA.next()) {
                int codProvincia = rsPA.getInt("COD_PROVINCIA");
                int codCanton = rsPA.getInt("COD_CANTON");
                int codDistrito = rsPA.getInt("COD_DISTRITO");
                int codBarrio = rsPA.getInt("COD_BARRIO");
                String descBarrio = rsPA.getString("DSC_BARRIO");
                int logActivo = rsPA.getInt("LOG_ACTIVO");

                Barrio barrio = new Barrio(codProvincia,
                        codCanton, codDistrito,codBarrio ,descBarrio, logActivo);

                listBarrio.add(barrio);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return listBarrio;
    }
    }
