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
@Named(value = "beanHorario")
@SessionScoped
public class beanHorarios implements Serializable {
 int idHorario;
    int estado;
    String idUsuario;
    String horario1;
    Date horarioT;
    
    //Validar
    String error;
    Horarios horarioObject = new Horarios();
    
    public beanHorarios() {
    }
    
    public LinkedList<SelectItem> ListaHorarios(String cliente) throws SNMPExceptions, SQLException {
       
        LinkedList<Horarios> lista= new LinkedList<>();
        HorariosDB horarioDB = new HorariosDB();
        
        lista= horarioDB.ListarHorarios(cliente); 
        
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Horario"));
        
        for (Horarios hor : lista) {
            int idHor = hor.getIdHorario();
            String hora = hor.getHorario();
            resultList.add(new SelectItem(idHor,hora));
        }
        return resultList;
    }
    
    public void AgregarHorario(String id) throws SNMPExceptions, SQLException, IOException{
        if(horarioT == null){
            this.setError("Debe digitar un horario");
            return;
        }
        SimpleDateFormat f = new SimpleDateFormat("HH:mm a");
        horario1 = f.format(horarioT);
        if( horario1.equals("00:00 AM")){
             this.setError("Debe digitar un horario valido");
              FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }else{        
         estado = 1;
         Horarios horari = new Horarios(estado, id, horario1);
         
         HorariosDB horDB = new HorariosDB();
         horDB.AgregarHorario(horari);
         this.setError("Horario Agregado!");
         this.setHorario1("");
         this.setHorarioT(null);
             FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }
    }
    
    public void EliminarHorario() throws SNMPExceptions, IOException{
         HorariosDB hor = new HorariosDB();
         if(idHorario == 0){
             this.setError("Debe seleccionar un horario");
         }else{
             hor.EliminarHorario(idHorario);
             this.setError("Horario Inactivo");
             FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }
     }
    
    public void ModificarHorario() throws SNMPExceptions{ 
         HorariosDB horariosDB = new HorariosDB();
         if(idHorario == 0){
             this.setError("Debe seleccionar un horario");
         }else{
            Horarios hora = horariosDB.HorarioModi(idHorario);
             this.setHorarioObject(hora);
         }
     }
    
     public void GuardarCambios() throws SNMPExceptions{
         HorariosDB horariosDB = new HorariosDB();
         if(horarioObject.getHorario().equals("")){
             this.setError("Debe digitar el horario");
         }else{
             horariosDB.ModificarHorario(horarioObject);
             this.setError("Horario Modificado!");
             this.horarioObject.setHorario("");
         }
     }

    public Date getHorarioT() {
        return horarioT;
    }

    public void setHorarioT(Date horarioT) {
        this.horarioT = horarioT;
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

    public String getHorario1() {
        return horario1;
    }

    public void setHorario1(String horario) {
        this.horario1 = horario;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Horarios getHorarioObject() {
        return horarioObject;
    }

    public void setHorarioObject(Horarios horarioObject) {
        this.horarioObject = horarioObject;
    }
    
    
}
