/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.seguridad;

/**
 *
 * @author GBD
 */
@Named(value = "beanSeguridad")
@SessionScoped
public class BeanSeguridad implements Serializable {
private String Usuario;
private String Clave;
private String error;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

   

   
    public BeanSeguridad() {
    }
    
    
    public String validarDatos() {
        String mensaje = "";

        seguridad s = new seguridad();
        if (!(Usuario.equals("") && Clave.equals("") )) {
            if (s.validarDatos(Usuario,Clave)) {
                mensaje = "MenuPrincipal.xhtml";
            } else {
                error = "Datos invalidos";
            }

        }else{
            error = "Datos invalidos";
        }
       
        return mensaje;
        
    }

}

