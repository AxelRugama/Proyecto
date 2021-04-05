
package Model;

import java.util.Date;

/**
 *
 * @author axelj
 */
public class Factura {
    int idFactura;
    int idPedido;
    String idUsuario;
    double descuento;
    double impuestoIVA;
    double precioTotal;
    String fecha;
    String tipoPago;
    int estado;

    public Factura() {
    }

    public Factura(int idFactura, int idPedido, String idUsuario, double descuento, double impuestoIVA, double precioTotal, String fecha, String tipoPago, int estado) {
        this.idFactura = idFactura;
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.descuento = descuento;
        this.impuestoIVA = impuestoIVA;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.tipoPago = tipoPago;
        this.estado = estado;
    }

    
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuestoIVA() {
        return impuestoIVA;
    }

    public void setImpuestoIVA(double impuestoIVA) {
        this.impuestoIVA = impuestoIVA;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
