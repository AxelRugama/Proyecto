/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.SNMPExceptions;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author GBD
 */
@Named(value = "beanDespacho")
@SessionScoped
public class beanDespacho implements Serializable {

     String idUsuario;
    LinkedList<Factura> listaFacturas = new LinkedList<>();
    String alerta;
    
    public beanDespacho() {
    }
    
    public LinkedList<SelectItem> ListarClientes() throws SNMPExceptions, SQLException {
        LinkedList<Usuario> lista = new LinkedList<>();
        UsuarioDB usuarioDB = new UsuarioDB();

        lista = usuarioDB.ListarClientes();

        LinkedList<SelectItem> resultList = new LinkedList();
        resultList.add(new SelectItem("Todos"));

        for (Usuario usua : lista) {
            String idCliente = usua.getIdent();
            resultList.add(new SelectItem(idCliente));
        }
        return resultList;
    }
    
    public void Despachar(int idFactura, String idUsua) throws SNMPExceptions, IOException{
        FacturaDB facturaDB = new FacturaDB();
        facturaDB.ModificarFactura(idFactura);
        
        DespachoDB despachoDB = new DespachoDB();
        
        Date fecha = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDB = f.format(fecha);
        Despacho des = new Despacho(idFactura, fechaDB, idUsua);
        
        despachoDB.AgregarDespacho(des);
        
       FacesContext.getCurrentInstance().getExternalContext().redirect("Despacho.xhtml");
    }
    
    public void ListarFacturas() throws SNMPExceptions, IOException{
        if(idUsuario == null || idUsuario.equals("Todos")){
        DespachoDB despachoDB = new DespachoDB();
        this.setListaFacturas(despachoDB.ListFacturas());
        if(listaFacturas.isEmpty()){
            this.setAlerta("No posee facturas pendientes");
            return;
        }
        this.setAlerta("");
        }
    }
    
    public void ListarFacturasCliente() throws SNMPExceptions, IOException{
        if(!idUsuario.equals("Todos")){
        DespachoDB despachoDB = new DespachoDB();
        this.setListaFacturas(despachoDB.ListFacCliente(idUsuario)); 
        if(listaFacturas.isEmpty()){
            this.setAlerta("No posee facturas pendientes");
            return;
        }
        this.setAlerta("");
        FacesContext.getCurrentInstance().getExternalContext().redirect("Despacho.xhtml");
        }
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    
    public LinkedList<Factura> getListaFacturas() throws SNMPExceptions, IOException {
        ListarFacturas();
        return listaFacturas;
    }

    public void setListaFacturas(LinkedList<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
