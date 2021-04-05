
package Model;

/**
 *
 * @author axelj
 */
public class Pedidos {
    int idPedido;
    String idUsuario;
    int idHorarioEntrega;
    int idDireccionEntrega;

    public Pedidos(int idPedido, String idUsuario, int idHorarioEntrega, int idDireccionEntrega) {
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
        this.idHorarioEntrega = idHorarioEntrega;
        this.idDireccionEntrega = idDireccionEntrega;
    }

    public Pedidos() {
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

    public int getIdHorarioEntrega() {
        return idHorarioEntrega;
    }

    public void setIdHorarioEntrega(int idHorarioEntrega) {
        this.idHorarioEntrega = idHorarioEntrega;
    }

    public int getIdDireccionEntrega() {
        return idDireccionEntrega;
    }

    public void setIdDireccionEntrega(int idDireccionEntrega) {
        this.idDireccionEntrega = idDireccionEntrega;
    }

   
    
    
}
