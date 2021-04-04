/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Provincia;
import Model.ProvinciaDB;
import Model.Canton;
import Model.CantonDB;
import Model.Distrito;
import Model.DistritoDB;
import Model.Barrio;
import Model.BarrioDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alberto
 */
@Named(value = "beanProvincias")
@SessionScoped
public class beanProvincias implements Serializable {

    //ATRIBUTOS PROVINCIA
    float cod_provincia;
    String dsc_corta_provincia;
    String dsc_provincia = "Alajuela";
    float log_activo;
    LinkedList<SelectItem> listaPro = new LinkedList<>();
    LinkedList<Provincia> listaTablaProvincia = new LinkedList<Provincia>();

    //provincia
    Provincia pro;
    ProvinciaDB proDB;

    //ATRIBUTOS CANTON
    float cod_provinciaCan;
    float cod_canton;
    String dsc_canton;
    float log_activoCan;
    LinkedList<SelectItem> listaCan = new LinkedList<>();

    Canton can;
    CantonDB canDB;
    
    //Atributos Distrito
    float cod_provinciaDis;
    float cod_cantonDist;
    float cod_distrito;
    String dsc_distrito;
    float log_activodist;
    LinkedList<SelectItem> listaDis = new LinkedList<>();

    Distrito dis;
    DistritoDB disDB;
    
    //Atributos Barrio
    float cod_provinciaBar;
    float cod_cantonBar;
    float cod_distritoBar;
    float cod_barrio;
    String dsc_barrio;
    float log_activobar;
    LinkedList<SelectItem> listaBar = new LinkedList<>();

    Distrito bar;
    DistritoDB barDB;
    
    
    
    
     public beanProvincias() {
    }
     
    public float getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(float cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public String getDsc_corta_provincia() {
        return dsc_corta_provincia;
    }

    public void setDsc_corta_provincia(String dsc_corta_provincia) {
        this.dsc_corta_provincia = dsc_corta_provincia;
    }

    public String getDsc_provincia() {
        return dsc_provincia;
    }

    public void setDsc_provincia(String dsc_provincia) {
        this.dsc_provincia = dsc_provincia;
    }

    public float getLog_activo() {
        return log_activo;
    }

    public void setLog_activo(float log_activo) {
        this.log_activo = log_activo;
    }
    
    
    public LinkedList<Provincia> getListaTablaProvincia() throws SNMPExceptions, SQLException {
        
        LinkedList<Provincia> lista = new LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultLista = new LinkedList();
           
        resultLista=lista;       
        return resultLista; 

    }

    public void setListaTablaProvincia(LinkedList<
            Provincia> listaTablaProvincia) {
        this.listaTablaProvincia = listaTablaProvincia;
    }
   

    public void setListPro(LinkedList<SelectItem> 
            listProv) {
        this.listaPro= listProv;
    }

    public LinkedList<SelectItem> getListaPro() 
            throws SNMPExceptions, SQLException{
        String dscCortaProvincia="";
        float codigoProvincia=0;
        
        LinkedList<Provincia> lista = new 
        LinkedList<Provincia>();
        ProvinciaDB pDB = new ProvinciaDB();
        
        lista = pDB.moTodo();
        
        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, 
                "Seleccione Provincia"));
        
        for (Iterator iter= lista.iterator();
                iter.hasNext();) {
        
            Provincia pro = (Provincia) iter.next();
            dscCortaProvincia=pro.getDsc_corta_provincia();
            codigoProvincia=pro.getCod_provincia();
            resultList.add(new SelectItem(codigoProvincia, 
                    dscCortaProvincia));
         }         
         return resultList; 
        
    }

    public void setListaPro(LinkedList<SelectItem> listaPro)
    {
        this.listaPro = listaPro;
    }

    
    //CANTON
    
    public float getCod_provinciaCan() {
        return cod_provinciaCan;
    }

    public void setCod_provinciaCan(float cod_provinciaCan) {
        this.cod_provinciaCan = cod_provinciaCan;
    }

    public float getCod_canton() {
        return cod_canton;
    }

    public void setCod_canton(float cod_canton) {
        this.cod_canton = cod_canton;
    }

    public String getDsc_canton() {
        return dsc_canton;
    }

    public void setDsc_canton(String dsc_canton) {
        this.dsc_canton = dsc_canton;
    }

    public float getLog_activoCan() {
        return log_activoCan;
    }

    public void setLog_activoCan(float log_activoCan) {
        this.log_activoCan = log_activoCan;
    }

    public LinkedList<SelectItem> getListaCan() throws SNMPExceptions, SQLException{
        
        String descripcion = "";
        float codigoCant = 0;

        LinkedList<Canton> lista = new LinkedList<Canton>();
        CantonDB cantonDB = new CantonDB();

        lista = cantonDB.SeleccionarCantonPorProvincia(cod_provincia);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Cantón"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Canton canton = (Canton) iter.next();
            descripcion = canton.getDsc_canton();
            codigoCant = canton.getCod_canton();
            resultList.add(new SelectItem(codigoCant, descripcion));

        }
        return resultList;
    }

    public void setListaCan(LinkedList<SelectItem> listaCan) {
        this.listaCan = listaCan;
    }
    
    //Distrito
    
     public float getCod_provinciaDis() {
        return cod_provinciaDis;
    }

    public void setCod_provinciaDis(float cod_provinciaDis) {
        this.cod_provinciaDis = cod_provinciaDis;
    }

    public float getCod_cantonDist() {
        return cod_cantonDist;
    }

    public void setCod_cantonDist(float cod_cantonDist) {
        this.cod_cantonDist = cod_cantonDist;
    }

    public float getCod_distrito() {
        return cod_distrito;
    }

    public void setCod_distrito(float cod_distrito) {
        this.cod_distrito = cod_distrito;
    }

    public String getDsc_distrito() {
        return dsc_distrito;
    }

    public void setDsc_distrito(String dsc_distrito) {
        this.dsc_distrito = dsc_distrito;
    }

    public float getLog_activodist() {
        return log_activodist;
    }

    public void setLog_activodist(float log_activodist) {
        this.log_activodist = log_activodist;
    }
   
    public LinkedList<SelectItem> getListaDis() throws SNMPExceptions, SQLException{
        String descripcion = "";
        float codigoDis = 0;

        LinkedList<Distrito> lista = new LinkedList<Distrito>();
        DistritoDB distritoDB = new DistritoDB();

        lista = distritoDB.SeleccionarDistritoporCanton(cod_provincia,cod_canton);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Distrito"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Distrito distrito = (Distrito) iter.next();
            descripcion = distrito.getDsc_Distrito();
            codigoDis = distrito.getCod_distrito();
            resultList.add(new SelectItem(codigoDis, descripcion));

        }
        return resultList;
        
    }

    public void setListaDis(LinkedList<SelectItem> listaDis) {
        this.listaDis = listaDis;
    }
    
    
    //Barrio
     public float getCod_provinciaBar() {
        return cod_provinciaBar;
    }

    public void setCod_provinciaBar(float cod_provinciaBar) {
        this.cod_provinciaBar = cod_provinciaBar;
    }

    public float getCod_cantonBar() {
        return cod_cantonBar;
    }

    public void setCod_cantonBar(float cod_cantonBar) {
        this.cod_cantonBar = cod_cantonBar;
    }

    public float getCod_distritoBar() {
        return cod_distritoBar;
    }

    public void setCod_distritoBar(float cod_distritoBar) {
        this.cod_distritoBar = cod_distritoBar;
    }

    public float getCod_barrio() {
        return cod_barrio;
    }

    public void setCod_barrio(float cod_barrio) {
        this.cod_barrio = cod_barrio;
    }

    public String getDsc_barrio() {
        return dsc_barrio;
    }

    public void setDsc_barrio(String dsc_barrio) {
        this.dsc_barrio = dsc_barrio;
    }

    public float getLog_activobar() {
        return log_activobar;
    }

    public void setLog_activobar(float log_activobar) {
        this.log_activobar = log_activobar;
    }

    public LinkedList<SelectItem> getListaBar() throws SNMPExceptions, SQLException{
       String descripcion = "";
        float codigoBar = 0;

        LinkedList<Barrio> lista = new LinkedList<Barrio>();
        BarrioDB barrioDB = new BarrioDB();

        lista = barrioDB.SeleccionarBarrioporDistrito(cod_provincia,cod_canton,cod_distrito);

        LinkedList resultList = new LinkedList();
        resultList.add(new SelectItem(0, "Seleccione Barrio"));

        for (Iterator iter = lista.iterator();
                iter.hasNext();) {

            Barrio barrio = (Barrio) iter.next();
            descripcion = barrio.getDsc_barrio();
            codigoBar = barrio.getCod_barrio();
            resultList.add(new SelectItem(codigoBar, descripcion));

        }
        return resultList;
    }

    public void setListaBar(LinkedList<SelectItem> listaBar) {
        this.listaBar = listaBar;
    }
}
