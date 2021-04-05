/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductoDB;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import org.primefaces.PrimeFaces;

/**
 *
 * @author GBD
 */
@Named(value = "beanProductos")
@SessionScoped
public class beanProductos implements Serializable {
 //Variables
    int id;
    String descripcion;
    String Imagen;
    Double precio;
    int cantidadMinima;
    int estado;

    //Validar
    int id2;
    Producto p = new Producto();

    //Listas
    LinkedList<Producto> listaProductos = new LinkedList<Producto>();
    LinkedList<Producto> listaProductosActivos = new LinkedList<>();

    //Mensajes
    String error;

    public beanProductos() {
    }

    public void mostrarLista() throws SNMPExceptions, SQLException {
        ProductoDB productoDB = new ProductoDB();
        this.setListaProductos(productoDB.ListarProductos());
    }

    public void mostrarListaActivos() throws SNMPExceptions, SQLException {
        ProductoDB productoDB = new ProductoDB();
        this.setListaProductosActivos(productoDB.ListarProductosActivos());
    }

    public void agregarProducto() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException, IOException {
        Producto p = new Producto(descripcion, Imagen, precio, cantidadMinima, estado);
        ProductoDB productoDB = new ProductoDB();

        if (this.descripcion.equals("") || this.Imagen.equals("") || this.precio == 0
                || this.cantidadMinima == 0) {
            this.setError("Debe completar los campos.");
        } else {
            productoDB.ingresarProducto(p);
            this.setError("Registro Exitoso!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantProductos.xhtml");
        }

    }

    public void productoModi() throws IOException, SNMPExceptions, SQLException {
        if (id2 == 0) {
            this.setError("Debe digitar el codigo del Producto");
        } else {
            ProductoDB productoDB = new ProductoDB();
            Producto producto = productoDB.productoModi(id2);
            if (producto == null) {
                this.setError("Codigo Invalido");
            } else {
                this.setP(producto);
                this.setError("");
                FacesContext.getCurrentInstance().getExternalContext().redirect("modificarProducto.xhtml");
            }
        }

    }

    public void modificarProducto(Producto p) throws SNMPExceptions, SQLException, IOException {
        if (p.getDescripcion().equals("") || p.getImagen().equals("") || p.getEstado_s().equals("") || p.getCantidadMinima() == 0 || p.getPrecio() == 0) {
            this.setError("Debe completar los Datos");
        } else {
            ProductoDB productoDB = new ProductoDB();
            productoDB.ModificarProducto(p);
            this.setError("Modificacion Exitosa!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("mantProductos.xhtml");
        }
    }

    public void eliminarProducto() throws SNMPExceptions, SQLException, IOException {
        if (id2 == 0) {
            this.setError("Debe digitar el codigo del producto");
        } else {
            ProductoDB productoDB = new ProductoDB();
            Producto pro = productoDB.productoModi(id2);
            if (pro == null) {
                this.setError("Codigo No Valido");
            } else {
                if (pro.getEstado_s().equals("Inactivo")) {
                    this.setError("Ya se encuentra inactivo");
                } else {
                    productoDB.eliminarProducto(id2);
                    this.setError("Producto Inactivo");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mantProductos.xhtml");
                }
            }
        }
    }

    public String Productos() throws SNMPExceptions, SQLException {
        LinkedList<Producto> lista = new LinkedList();
        String productos = "";
        ProductoDB pro = new ProductoDB();
        lista = pro.ListarProductos();
        for (Producto producto : lista) {
            productos += "<div class=\"col-md-4\"> Descripcion: " + producto.getDescripcion() + "</div>";
        }
        return productos;

    }

    public LinkedList<Producto> getListaProductosActivos() throws SNMPExceptions, SQLException {
        mostrarListaActivos();
        return listaProductosActivos;
    }

    public void setListaProductosActivos(LinkedList<Producto> listaProductosActivos) {
        this.listaProductosActivos = listaProductosActivos;
    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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

    public LinkedList<Producto> getListaProductos() throws SNMPExceptions, SQLException {
        mostrarLista();
        return listaProductos;
    }

    public void setListaProductos(LinkedList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @PostConstruct
    public void crearDiv() {
        try {
            ProductoDB db = new ProductoDB();
            for (Producto pro : db.ListarProductos()) {
                PrimeFaces.current().executeScript("hacerDiv(" + pro.getDescripcion() + "," + pro.getPrecio() + "," + pro.getImagen() + ")");
            }

        } catch (Exception e) {
        }

    }

}
