/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.SNMPExceptions;
import Model.Factura;
import Model.FacturaDB;
import Model.LineaDetalle;
import Model.LineaDetalleDB;
import Model.PedidosDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author GBD
 */
@Named(value = "beanFactura")
@SessionScoped
public class beanFactura implements Serializable {


     //Lineas de detalle
    LinkedList<LineaDetalle> listaLineas = new LinkedList<>();
    
    //Informacion sobre el pedido
    String direccionEntrega;
    String horarioEntrega;
    
    //Factura
    Factura factura;
    LinkedList<Factura> listFac = new LinkedList<>();
    
    
    public beanFactura() {
    }
    
    public void CargarFactura(int idFac, String alerta) throws SNMPExceptions{
        if(alerta.equals("Realizado")){
            FacturaDB facturaDB = new FacturaDB();
            this.setFactura(facturaDB.RetornaFactura(idFac));
            this.setListFac(facturaDB.ListFac(idFac));
            LineaDetalleDB detalleDB = new LineaDetalleDB();
            this.setListaLineas(detalleDB.ListarLineas(idFac));
            
            PedidosDB pedidosDB = new PedidosDB();
            this.setDireccionEntrega(pedidosDB.RetornaDireccion(factura.getIdPedido()));
            this.setHorarioEntrega(pedidosDB.RetornaHorario(factura.getIdPedido()));
        }
    }

    public LinkedList<Factura> getListFac() {
        return listFac;
    }

    public void setListFac(LinkedList<Factura> listFac) {
        this.listFac = listFac;
    }

    
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public LinkedList<LineaDetalle> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(LinkedList<LineaDetalle> listaLineas) {
        this.listaLineas = listaLineas;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(String horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }
    
}
    
