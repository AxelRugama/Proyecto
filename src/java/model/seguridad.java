/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GBD
 */
public class seguridad {
       private String user="Admin";
    private String pass="Admin";
    


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

   
  
    
    
    
    
    
    public boolean validarDatos(String usuario, String clave){
        boolean aux=false;
        if(usuario.equals(user)){
            if(clave.equals(pass)){
                
                    aux= true;
                
            }
        }
        
        return aux;
    }

}

