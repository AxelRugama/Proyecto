
package Model;

/**
 *
 * @author axelj
 */
public class LineaDetalle {
    int idLinea;
    int idFactura;
    int idProducto;
    Double precioProducto;
    int cantidadProducto;
    String descripcionProducto;

    public LineaDetalle() {
    }

    public LineaDetalle(int idLinea, int idFactura, int idProducto, Double precioProducto, int cantidadProducto, String descripcion) {
        this.idLinea = idLinea;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
        this.descripcionProducto = descripcion;
    }

    public LineaDetalle(int idLinea, Double precioProducto, int cantidadProducto, String descripcionProducto) {
        this.idLinea = idLinea;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
        this.descripcionProducto = descripcionProducto;
    }
    

    public int getIdLinea() {
        return idLinea;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    
    

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    
    
}
