/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author axelj
 */
public class Usuario {
    String ident;
    String nombre;
    String apellido;
    String telefono;
    String correo;
    String pass;
    String rol_s;
    String estado_s;
    int rol;
    int estado;

    public Usuario(String ident, String nombre, String apellido, String telefono, String correo, String pass, int rol, int estado) {
        this.ident = ident;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.pass = pass;
        this.rol = rol;
        this.estado = estado;
    }

    public Usuario() {
    }
    
    public Usuario(String ident, String nombre, String apellido, String telefono, String correo, String rol_s, String estado_s) {
        this.ident = ident;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.rol_s = rol_s;
        this.estado_s = estado_s;
    }

    public Usuario(String ident, String nombre, String apellido, String telefono, String correo, String estado_s) {
        this.ident = ident;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.estado_s = estado_s;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol_s() {
        return rol_s;
    }

    public void setRol_s(String rol_s) {
        this.rol_s = rol_s;
    }

    public String getEstado_s() {
        return estado_s;
    }

    public void setEstado_s(String estado_s) {
        this.estado_s = estado_s;
    }
    
    
}
