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
import java.util.LinkedList;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author GBD
 */
@Named(value = "beanUsuarios")
@SessionScoped
public class beanUsuarios implements Serializable {
String identidad;
    String ident;
    String nombre;
    String apellido;
    String pass;
    String pass2;
    String error;
    String error2;
    String mensaje;
    String mensajeC;
    String mensajeU;
    String mensajeConfi;
    String passActual;
    int cambiar = 0;
    int rol;
    int estado;
    LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
    LinkedList<Usuario> listaClientes = new LinkedList<Usuario>();
    Usuario u = null;
    Usuario u2 = null;
    Usuario uconfi = null;

    public beanUsuarios() {
    }

    public void insertarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        rol = 3;
        estado = 2;
        Usuario u = new Usuario(ident, nombre, apellido, pass, rol, estado);
        UsuarioDB usuarioDB = new UsuarioDB();
        if (this.ident.equals("") || this.nombre.equals("") || this.apellido.equals("")
                || this.pass.equals("") || this.pass2.equals("")) {
            this.setMensaje("Debe completar los campos.");
            return;
        }

        if (!this.pass.equals(pass2)) {
            this.setMensaje("Las contraseñas no coinciden");
            return;
        }
        mostrarLista();
        mostrarListaClientes();
        for (Usuario listaUsuario : listaUsuarios) {
            if (ident.equals(listaUsuario.getIdent())) {
                this.setMensaje("Ya existe un usuario con el mismo Correo Electronico");
                return;
            }
        }
        for (Usuario listaCliente : listaClientes) {
            if (ident.equals(listaCliente.getIdent())) {
                this.setMensaje("Ya existe un usuario con el mismo Correo Electronico");
                return;
            }
        }
        usuarioDB.ingresarUsuario(u);
        this.setMensaje("");
        this.setEstado(0);
        this.setRol(0);
        this.setIdent("");
        this.setNombre("");
        this.setApellido("");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void Cancelar() throws IOException {
        this.setMensaje("");
        this.setEstado(0);
        this.setRol(0);
        this.setIdent("");
        this.setNombre("");
        this.setApellido("");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void CancelarCliente() throws IOException {
        this.setMensajeC("");
        this.setEstado(0);
        this.setRol(0);
        this.setIdent("");
        this.setNombre("");
        this.setApellido("");
        FacesContext.getCurrentInstance().getExternalContext().redirect("mantClientes.xhtml");
    }

    public void CancelarUsuario() throws IOException {
        this.setMensajeU("");
        this.setEstado(0);
        this.setRol(0);
        this.setIdent("");
        this.setNombre("");
        this.setApellido("");
        FacesContext.getCurrentInstance().getExternalContext().redirect("mantUsuarios.xhtml");
    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        UsuarioDB usuarioDB = new UsuarioDB();
        this.setListaUsuarios(usuarioDB.ListarUsuarios());
    }

    public void mostrarListaClientes() throws SNMPExceptions, SQLException {
        UsuarioDB usuarioDB = new UsuarioDB();
        this.setListaClientes(usuarioDB.ListarClientes());
    }

    public void modiCliente() throws SNMPExceptions, SQLException, IOException {
        if (identidad.equals("")) {
            this.setError("Debe digitar el correo del cliente");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            Usuario usuario = usuarioDB.clienteModi(identidad);
            if (usuario == null) {
                this.setError("Correo No Valido");
            } else {
                this.setU(usuario);
                this.setError("");
                this.setIdent2("");
                FacesContext.getCurrentInstance().getExternalContext().redirect("modificarCliente.xhtml");
            }
        }
    }

    public void modificarCliente(Usuario u) throws SNMPExceptions, SQLException, IOException {
        if (u.getNombre().equals("") || u.getApellido().equals("") || u.getEstado_s().equals("")) {
            this.setError("Debe completar los Datos");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            usuarioDB.ModificarCliente(u);
            this.setError("Modificacion Exitosa!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantClientes.xhtml");
        }
    }

    public void modiUsuario() throws SNMPExceptions, SQLException, IOException {
        if (identidad.equals("")) {
            this.setError2("Debe digitar el correo del cliente");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            Usuario usuario = usuarioDB.usuarioModi(identidad);
            if (usuario == null) {
                this.setError2("Correo No Valido");
            } else {
                this.setU2(usuario);
                this.setError2("");
                this.setIdent2("");
                FacesContext.getCurrentInstance().getExternalContext().redirect("modificarUsuario.xhtml");
            }
        }
    }

    public void modificarUsuario(Usuario u) throws SNMPExceptions, SQLException, IOException {

        if (u.getNombre().equals("") || u.getApellido().equals("")
                || u.getEstado_s().equals("")) {
            this.setError2("Debe completar los Datos");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            usuarioDB.ModificarUsuario(u);
            this.setError2("Modificacion Exitosa!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantUsuarios.xhtml");
        }
    }

    public void modiConfi(String idUsuario) throws SNMPExceptions, SQLException, IOException {
        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario usuario = usuarioDB.usuarioModi(idUsuario);
        this.setUconfi(usuario);
        FacesContext.getCurrentInstance().getExternalContext().redirect("Configuracion.xhtml");

    }

    public void modificarConfi(Usuario uConfi) throws SNMPExceptions, SQLException, IOException {
        UsuarioDB usuarioDB = new UsuarioDB();
/*abre*/if(cambiar == 1){
        if (uConfi.getNombre().equals("") || uConfi.getApellido().equals("") || uConfi.getPass().equals("") || passActual.equals("")) {
            this.setMensajeConfi("Debe completar los Datos");
            return;
        } else {
            if(usuarioDB.ValidarContrasenna(uConfi,passActual) == false){
                this.setMensajeConfi("Contraseña actual incorrecta");
                return;
            }
        }
        usuarioDB.ModificarConfi(uConfi);
        this.setMensajeConfi("Modificacion Exitosa!");
        
  /*Cierra*/}else{
            if (uConfi.getNombre().equals("") || uConfi.getApellido().equals("") || passActual.equals("")) {
            this.setMensajeConfi("Debe completar los Datos");
            return;
        }else {
            if(usuarioDB.ValidarContrasenna(uConfi,passActual) == false){
                this.setMensajeConfi("Contraseña actual incorrecta");
                return;
            }
        }
        usuarioDB.ModificarUsuario(uConfi);
        this.setMensajeConfi("Modificacion Exitosa!");
        }
    }

    public void eliminarCliente() throws SNMPExceptions, SQLException, IOException {
        if (identidad.equals("")) {
            this.setError("Debe digitar el correo del cliente");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            Usuario u = usuarioDB.clienteModi(identidad);
            if (u == null) {
                this.setError("Correo No Valido");
            } else {
                if (u.getEstado_s().equals("Inactivo")) {
                    this.setError("Ya se encuentra inactivo");
                } else {
                    usuarioDB.eliminarCliente(identidad);
                    this.setError("Cliente Inactivo");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mantClientes.xhtml");
                }
            }
        }
    }

    public void eliminarUsuario() throws SNMPExceptions, SQLException, IOException {
        if (identidad.equals("")) {
            this.setError2("Debe digitar el correo del cliente");
        } else {
            UsuarioDB usuarioDB = new UsuarioDB();
            Usuario u = usuarioDB.usuarioModi(identidad);
            if (u == null) {
                this.setError("Correo No Valido");
            } else {
                if (u.getEstado_s().equals("Inactivo")) {
                    this.setError2("Ya se encuentra inactivo");
                } else {
                    usuarioDB.eliminarCliente(identidad);
                    this.setError2("Usuario Inactivo");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mantUsuarios.xhtml");
                }
            }
        }
    }

    public void agregarCliente() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        rol = 3;
        estado = 2;
        Usuario u = new Usuario(ident, nombre, apellido, pass, rol, estado);
        UsuarioDB usuarioDB = new UsuarioDB();

        if (this.ident.equals("") || this.nombre.equals("") || this.apellido.equals("")
                || this.pass.equals("") || this.pass2.equals("")) {
            this.setMensajeC("Debe completar los campos.");

        } else {
            if (!this.pass.equals(pass2)) {
                this.setMensajeC("Las contraseñas no coinciden");
            } else {
                mostrarLista();
                mostrarListaClientes();
                for (Usuario listaUsuario : listaUsuarios) {
                    if (ident.equals(listaUsuario.getIdent())) {
                        this.setMensajeC("Ya existe un usuario con el mismo Correo Electronico");
                        return;
                    }
                }
                for (Usuario listaCliente : listaClientes) {
                    if (ident.equals(listaCliente.getIdent())) {
                        this.setMensajeC("Ya existe un usuario con el mismo Correo Electronico");
                        return;
                    }
                }
                usuarioDB.ingresarUsuario(u);
                this.setError("Registro Exitoso!");
                this.setEstado(0);
                this.setRol(0);
                this.setIdent("");
                this.setNombre("");
                this.setApellido("");
                FacesContext.getCurrentInstance().getExternalContext().redirect("mantClientes.xhtml");
            }
        }
    }

    public void agregarUsuario() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        rol = 1;
        estado = 1;
        Usuario u = new Usuario(ident, nombre, apellido, pass, rol, estado);
        UsuarioDB usuarioDB = new UsuarioDB();

        if (this.ident.equals("") || this.nombre.equals("") || this.apellido.equals("")
                || this.pass.equals("") || this.pass2.equals("")) {
            this.setMensajeU("Debe completar los campos.");

        } else {
            if (!this.pass.equals(pass2)) {
                this.setMensajeU("Las contraseñas no coinciden");
            } else {
                mostrarLista();
                mostrarListaClientes();
                for (Usuario listaUsuario : listaUsuarios) {
                    if (ident.equals(listaUsuario.getIdent())) {
                        this.setMensajeU("Ya existe un usuario con el mismo Correo Electronico");
                        return;
                    }
                }
                for (Usuario listaCliente : listaClientes) {
                    if (ident.equals(listaCliente.getIdent())) {
                        this.setMensajeU("Ya existe un usuario con el mismo Correo Electronico");
                        return;
                    }
                }
                usuarioDB.ingresarUsuario(u);
                this.setError2("Registro Exitoso!");
                this.setEstado(0);
                this.setRol(0);
                this.setIdent("");
                this.setNombre("");
                this.setApellido("");
                FacesContext.getCurrentInstance().getExternalContext().redirect("mantUsuarios.xhtml");
            }
        }
    }

    public int getCambiar() {
        return cambiar;
    }

    public void setCambiar(int cambiar) {
        this.cambiar = cambiar;
    }

    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public String getMensajeConfi() {
        return mensajeConfi;
    }

    public void setMensajeConfi(String mensajeConfi) {
        this.mensajeConfi = mensajeConfi;
    }

    public String getMensajeU() {
        return mensajeU;
    }

    public void setMensajeU(String mensajeU) {
        this.mensajeU = mensajeU;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeC() {
        return mensajeC;
    }

    public void setMensajeC(String mensajeC) {
        this.mensajeC = mensajeC;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }

    public Usuario getU2() {
        return u2;
    }

    public void setU2(Usuario u2) {
        this.u2 = u2;
    }

    public Usuario getUconfi() {
        return uconfi;
    }

    public void setUconfi(Usuario uconfi) {
        this.uconfi = uconfi;
    }

    public LinkedList<Usuario> getListaClientes() throws SNMPExceptions, SQLException {
        mostrarListaClientes();
        this.setIdent2("");
        this.setError2("");
        return listaClientes;
    }

    public void setListaClientes(LinkedList<Usuario> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public LinkedList<Usuario> getListaUsuarios() throws SNMPExceptions, SQLException {
        UsuarioDB usuarioDB = new UsuarioDB();
        this.setListaUsuarios(usuarioDB.ListarUsuarios());
        this.setIdent("");
        this.setError("");
        return listaUsuarios;
    }

    public void setListaUsuarios(LinkedList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public String getIdent2() {
        return identidad;
    }

    public void setIdent2(String ident2) {
        this.identidad = ident2;
    }

}
