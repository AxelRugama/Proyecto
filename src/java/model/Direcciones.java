
package Model;

/**
 *
 * @author axelj
 */
public class Direcciones {
    String idUsuario;
    String direccion;
    String provincia;
    String distrito;
    String cantones;
    String barrios;
    int idDireccion;
    int estado;

    public Direcciones(String idUsuario, String direccion, int idDireccion, int estado,String provincia,String distrito,String cantones,String barrios ) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.idDireccion = idDireccion;
        this.estado = estado;
        this.provincia = provincia;
        this.cantones = cantones;
        this.distrito = distrito;
        this.barrios = barrios;
    }

    public Direcciones(String idUsuario, String direccion, int estado, String provincia,String distrito,String cantones,String barrios) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.estado = estado;
        this.provincia = provincia;
        this.cantones = cantones;
        this.distrito = distrito;
        this.barrios = barrios;
    }

    public Direcciones() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCantones() {
        return cantones;
    }

    public void setCantones(String cantones) {
        this.cantones = cantones;
    }

    public String getBarrios() {
        return barrios;
    }

    public void setBarrios(String barrios) {
        this.barrios = barrios;
    }
    
    
    
    
}
