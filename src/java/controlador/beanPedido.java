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
import Model.Pedidos;
import Model.PedidosDB;
import Model.Producto;
import Model.ProductoDB;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.faces.context.FacesContext;

/**
 *
 * @author GBD
 */
@Named(value = "beanPedido")
@SessionScoped
public class beanPedido implements Serializable {

    //Productos Variables
    LinkedList<Producto> listaProductos = new LinkedList<>();
    Double subTotal;
    Producto pro;
    
    //Validaciones
    String error;
    String alerta;
    
    //Lineas de Detalle Variables
    LinkedList<LineaDetalle> listaLineas = new LinkedList<>();
    int idLinea = 1;
    int cantidadProducto;

    //Factura Variables
    
    int idFactura;
    Date fecha = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    String fechaDB = format.format(fecha);
    double precioTotal;
    double descuento = 0.10;
    double IVA = 0.13;
    double descDB;
    double ivaDB;
    String tipoPago;
    int estadoFactura = 1;
    
    
    //Pedido Variables
    
    int idPedido;
    int idDireccion;
    int idHorario;
    
    //Para ValidarFactura
    int idFacturaMostrar;
    String alertaMostrar;
    
    public beanPedido() {
        
    }
    
    //METODO PRINCIPAL   FACTURA / PEDIDO / LINEAS DETALLE
    public void Facturar(String idUsuario) throws SNMPExceptions, IOException{
        if(listaLineas.size() == 0){
            this.setAlerta("Aun no se han registrado Productos");
            return;
        }
        if(idDireccion == 0){
            this.setAlerta("Debe seleccionar una direccion de entrega");
            return;
        }
        if(idHorario == 0){
            this.setAlerta("Debe seleccionar un horario de entrega");
            return;
        }
        if(tipoPago == null){
            this.setAlerta("Debe seleccionar el metodo de pago");
            return;
        }
        
        GuardarPedido(idUsuario);
        GuardarFactura(idUsuario);
        GuardarLineas();
        
        
        this.setAlertaMostrar("Realizado");
        this.setIdFacturaMostrar(idFactura);
        
        this.listaLineas.clear();
        this.setError("");
        this.setAlerta("");
        this.setIdDireccion(0);
        this.setIdHorario(0);
        this.setTipoPago(null);
        this.setSubTotal(0.0);
        this.setPrecioTotal(0.0);
        this.setDescDB(0.0);
        this.setIvaDB(0.0);
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("Factura.xhtml");
    }
    
    public void Cancelar() throws IOException{
        this.listaLineas.clear();
        this.setError("");
        this.setAlerta("");
        this.setIdDireccion(0);
        this.setIdHorario(0);
        this.setTipoPago(null);
        this.setSubTotal(0.0);
        this.setPrecioTotal(0.0);
        this.setDescDB(0.0);
        this.setIvaDB(0.0);
    }
   
    //Lineas de Detalle Metodos
    public void AgregarLinea(int idProducto, double precioProducto) throws IOException, SNMPExceptions, SQLException {
        double sub = 0.0;
        ProductoDB productoDB = new ProductoDB();
        FacturaDB facturaDB = new FacturaDB();
        this.setIdFactura(facturaDB.RetornaIdFactura() + 1);
        if (cantidadProducto == 0) {
            this.setError("Debe digitar la cantidad que desea!");
            this.setAlerta("");
            return;
        }
        if (!listaLineas.isEmpty()) {
            for (int i = 0; i < listaLineas.size(); i++) {
                LineaDetalle line = listaLineas.get(i);
                if (line.getIdProducto() == idProducto) {
                    int nuevo = line.getCantidadProducto() + cantidadProducto;
                    line.setCantidadProducto(nuevo);
                    this.setError("Cantidad Modificada");
                    this.setAlerta("");
                    for (LineaDetalle listaL : listaLineas) {
                        sub += listaL.getPrecioProducto() * listaL.getCantidadProducto();
                    }
                    this.setSubTotal(sub);
                    CalcularTotal();
                    this.setCantidadProducto(0);
                    return;
                }
            }
        } else {
            pro = productoDB.productoModi(idProducto);
            if(cantidadProducto < pro.getCantidadMinima() ){
                this.setError("La cantidad debe ser igual o mayor a la cantidad minima");
                this.setAlerta("");
                return;
            }
            LineaDetalle linea = new LineaDetalle(idLinea, idFactura, idProducto, precioProducto, cantidadProducto, pro.getDescripcion());
            this.listaLineas.add(linea);
            this.idLinea++;
            for (LineaDetalle listaL : listaLineas) {
                sub += listaL.getPrecioProducto() * listaL.getCantidadProducto();
            }
            this.setSubTotal(sub);
            CalcularTotal();
            this.setCantidadProducto(0);
            this.setError("");
            this.setAlerta("");
            return;
        }
        pro = productoDB.productoModi(idProducto);
        if (cantidadProducto < pro.getCantidadMinima()) {
            this.setError("La cantidad debe ser igual o mayor a la cantidad minima");
            this.setAlerta("");
            return;
        }
        LineaDetalle linea = new LineaDetalle(idLinea, idFactura, idProducto, precioProducto, cantidadProducto, pro.getDescripcion());
        this.listaLineas.add(linea);
        this.idLinea++;
        for (LineaDetalle listaL : listaLineas) {
            sub += listaL.getPrecioProducto() * listaL.getCantidadProducto();
        }
        this.setSubTotal(sub);
        CalcularTotal();
        this.setCantidadProducto(0);
        this.setError("");
        this.setAlerta("");
    }
    
    public void BorrarLinea(int idProducto) {
        Double sub = 0.0;
        for (int i = 0; i < listaLineas.size(); i++) {
            LineaDetalle line = listaLineas.get(i);
            if (line.getIdProducto() == idProducto) {
                listaLineas.remove(i);
                this.setError("");
            }
        }

        for (LineaDetalle lista1 : listaLineas) {
            sub += lista1.getPrecioProducto() * lista1.getCantidadProducto();
        }
        this.setSubTotal(sub);
        CalcularTotal();
    }
    
    public void GuardarLineas() throws SNMPExceptions{
        LineaDetalleDB lineaDB = new LineaDetalleDB();
        for (LineaDetalle linea : listaLineas) {
            lineaDB.GuardarLineas(linea);
        }
    }
    
    
    //Factura Metodos
    public void CalcularTotal(){
        double total;
        if(subTotal >= 10000){
            total = subTotal - (subTotal * descuento);
            precioTotal = total + (total * IVA);
            this.setDescDB(subTotal * descuento);
            this.setIvaDB(total * IVA);
        }else{
            precioTotal = subTotal + (subTotal * IVA);
            this.setDescDB(0);
            this.setIvaDB(subTotal * IVA);
        }
    }
    
    public void GuardarFactura(String idUsuario) throws SNMPExceptions{
        FacturaDB facDB = new FacturaDB();
        Factura fac = new Factura(idFactura, idPedido, idUsuario, descDB, ivaDB, precioTotal, fechaDB, tipoPago, estadoFactura);
        
        facDB.GuardarFactura(fac);
    }
    
    //Pedido Metodos
    public void GuardarPedido(String idUsuario) throws SNMPExceptions{
        PedidosDB pedidosDB = new PedidosDB();
        this.setIdPedido(pedidosDB.RetornaIdPedido() + 1);
        Pedidos ped = new Pedidos(idPedido, idUsuario, idHorario, idDireccion);
        
        pedidosDB.GuardarPedido(ped);
    }
    
     //Get and Set

    public String getAlertaMostrar() {
        return alertaMostrar;
    }

    public void setAlertaMostrar(String alertaMostrar) {
        this.alertaMostrar = alertaMostrar;
    }

    public int getIdFacturaMostrar() {
        return idFacturaMostrar;
    }

    public void setIdFacturaMostrar(int idFacturaMostrar) {
        this.idFacturaMostrar = idFacturaMostrar;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }
    
    public String getFechaDB() {
        return fechaDB;
    }

    public void setFechaDB(String fechaDB) {
        this.fechaDB = fechaDB;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getDescDB() {
        return descDB;
    }

    public void setDescDB(double descDB) {
        this.descDB = descDB;
    }

    public double getIvaDB() {
        return ivaDB;
    }

    public void setIvaDB(double ivaDB) {
        this.ivaDB = ivaDB;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {    
        this.tipoPago = tipoPago;
    }

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public LinkedList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public LinkedList<LineaDetalle> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(LinkedList<LineaDetalle> listaLineas) {
        this.listaLineas = listaLineas;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

