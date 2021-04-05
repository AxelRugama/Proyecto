
package Model;

/**
 *
 * @author axelj
 */
public class Producto {
     int idProducto;
     String descripcion;
     String Imagen;
     double precio;
     int cantidadMinima;
     int estado;
     String estado_s;
     
    public Producto(String descripcion, String Imagen, double precio, int cantidadMinima, int estado) {
        this.descripcion = descripcion;
        this.Imagen = Imagen;
        this.precio = precio;
        this.cantidadMinima = cantidadMinima;
        this.estado = estado;
    }
    
        public Producto() {
    }

    public Producto(int idProducto, String descripcion, String Imagen, double precio, int cantidadMinima, String estado_s) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.Imagen = Imagen;
        this.precio = precio;
        this.cantidadMinima = cantidadMinima;
        this.estado_s = estado_s;
    }

    public String getEstado_s() {
        return estado_s;
    }

    public void setEstado_s(String estado_s) {
        this.estado_s = estado_s;
    }
        
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
     
     
}
