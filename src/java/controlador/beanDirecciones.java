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
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author GBD
 */
@Named(value = "beanDirecciones")
@SessionScoped
public class beanDirecciones implements Serializable {
String idUsuario;
        String direccion;
        int idDireccion;
        int estado;
        int idDirecInactiva;
        
        //Validar
        String errorDireccion;
                
        
        Direcciones direcObject = new Direcciones();
        
        
    public beanDirecciones() { 
        
    }
    
     public LinkedList<SelectItem> ListaDirecciones(String cliente) throws SNMPExceptions, SQLException {
       
        LinkedList<Direcciones> lista= new LinkedList<>();
        DireccionesDB direccionDB= new DireccionesDB();
        
        lista= direccionDB.ListarDirecciones(cliente); 
        
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Direccion"));
        
            for (Direcciones direc : lista) {
                int idDireccion = direc.getIdDireccion();
                String direccion = direc.getDireccion();
                resultList.add(new SelectItem(idDireccion, direccion));
            }
        return resultList;
    }
     
      public LinkedList<SelectItem> ListaDireccionesI(String cliente) throws SNMPExceptions, SQLException {
       
        LinkedList<Direcciones> lista= new LinkedList<>();
        DireccionesDB direccionDB= new DireccionesDB();
        
        lista= direccionDB.ListarDireccionesI(cliente); 
        
        LinkedList resultList= new LinkedList();
        resultList.add(new SelectItem(0,"Seleccione Direccion"));
        
        for(Iterator iter= lista.iterator(); iter.hasNext();){
            
            Direcciones direc= (Direcciones)iter.next();
            int idDireccion = direc.getIdDireccion();
            String direccion = direc.getDireccion();
            resultList.add(new SelectItem(idDireccion,direccion));
            
        }
        return resultList;
    }
      
     public String agregarDireccion(String id) throws SNMPExceptions, IOException, SQLException{
         if(direccion.equals("")){
             this.setErrorDireccion("Debe digitar una direccion");
         }else{
             DireccionesDB direct = new DireccionesDB();
             LinkedList<Direcciones> lista = new LinkedList<>();
             lista = direct .ListarDirecciones(id);
             for (Direcciones direcciones : lista) {
                 if(direccion.equals(direcciones.getDireccion())){
                     this.setErrorDireccion("Direccion ya existente");
                     FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
                     return "";
                 }
             }
         estado = 1;
         Direcciones direc = new Direcciones(id, direccion, estado);
         
         DireccionesDB direcDB = new DireccionesDB();
         direcDB.AgregarDireccion(direc);
         this.setErrorDireccion("Direccion Agregada!");
         this.setDireccion("");
             FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }
         return "";
     }
     
     public void EliminarDireccion() throws SNMPExceptions, IOException{
         DireccionesDB direc = new DireccionesDB();
         if(idDireccion == 0){
             this.setErrorDireccion("Debe seleccionar una direccion");
         }else{
             direc.EliminarDireccion(idDireccion);
             this.setErrorDireccion("Direccion Inactiva");
             FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }
     }
     
     public void ActivarDireccion() throws SNMPExceptions, IOException{
         DireccionesDB direc = new DireccionesDB();
         if(idDirecInactiva == 0){
             this.setErrorDireccion("Debe seleccionar una direccion inactiva");
         }else{
             direc.ActivarDireccion(idDirecInactiva);
             this.setErrorDireccion("Direccion Activa");
             FacesContext.getCurrentInstance().getExternalContext().redirect("Administrar.xhtml");
         }
     }
     
     public void ModificarDireccion() throws SNMPExceptions{ 
         DireccionesDB direc = new DireccionesDB();
         if(idDireccion == 0){
             this.setErrorDireccion("Debe seleccionar una direccion");
         }else{
             Direcciones direccion = direc.DireccionModi(idDireccion);
             this.setDirecObject(direccion);
         }
     }
     
     public void GuardarCambios() throws SNMPExceptions{
         DireccionesDB direc = new DireccionesDB();
         if(direcObject.getDireccion().equals("")){
             this.setErrorDireccion("Debe digitar la direccion");
         }else{
             direc.ModificarDireccion(direcObject);
             this.setErrorDireccion("Direccion Modificada!");
             this.direcObject.setDireccion("");
         }
     }
     
     

    public void cargarIdDireccion(){
        this.setIdDireccion(idDireccion);
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getErrorDireccion() {
        return errorDireccion;
    }

    public void setErrorDireccion(String errorDireccion) {
        this.errorDireccion = errorDireccion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdDirecInactiva() {
        return idDirecInactiva;
    }

    public void setIdDirecInactiva(int idDirecInactiva) {
        this.idDirecInactiva = idDirecInactiva;
    }

    public Direcciones getDirecObject() {
        return direcObject;
    }

    public void setDirecObject(Direcciones direcObject) {
        this.direcObject = direcObject;
    }
    
    
}
