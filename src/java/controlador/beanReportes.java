/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import DAO.SNMPExceptions;
import Model.FacturaDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author GBD
 */

@Named(value = "beanReportes")
@SessionScoped
public class beanReportes implements Serializable {
private PieChartModel pieModel;
    private BarChartModel barModel;
    
    public beanReportes() {
    }

    private void createPieModel() throws SNMPExceptions {
        pieModel = new PieChartModel();

        pieModel.set("Pendientes", Pendientes());
        pieModel.set("Entregados", Entregados());

        pieModel.setTitle("Pedidos");
        pieModel.setLegendPosition("a");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(300);
        pieModel.setShadow(false);
    }
    
    
    private void createBarModel() throws SNMPExceptions {
        barModel = initBarModel();
 
        barModel.setTitle("Facturas");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Tipo de Pago");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }
    
    private BarChartModel initBarModel() throws SNMPExceptions {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Contado");
        boys.set(" ", Contado());
 
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Credito");
        girls.set(" ", CuentaPorCobrar());
        
        model.addSeries(boys);
        model.addSeries(girls);
 
        return model;
    }

    public int Pendientes() throws SNMPExceptions {
        FacturaDB fac = new FacturaDB();
        return fac.Pendientes();
    }

    public int Entregados() throws SNMPExceptions{
        FacturaDB fac = new FacturaDB();
        return fac.Entregados();
    }
    
    public int Contado() throws SNMPExceptions{
        FacturaDB fac = new FacturaDB();
        return fac.Contado();
    }
    
    public int CuentaPorCobrar() throws SNMPExceptions{
        FacturaDB fac = new FacturaDB();
        return fac.CuentaPorCobrar();
    }

    public BarChartModel getBarModel() throws SNMPExceptions {
        createBarModel();
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    
    
    public PieChartModel getPieModel() throws SNMPExceptions {
        createPieModel();
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

}

