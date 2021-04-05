
package Model;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;


/**
 *
 * @author axelj
 */
public class Horarios {
    int idHorario;
    int estado;
    String idUsuario;
    String horario;
    

    public Horarios() {
    }

    public Horarios(int estado, String idUsuario, String horario) {
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.horario = horario;
    }

    public Horarios(int idHorario, int estado, String idUsuario, String horario) {
        this.idHorario = idHorario;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.horario = horario;
        
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }   
    
}
