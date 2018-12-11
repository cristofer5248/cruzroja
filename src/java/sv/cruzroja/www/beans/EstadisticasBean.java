/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sv.cruzroja.www.entities.BeneficiadosEntity;
import sv.cruzroja.www.model.ProyectosBeneficiadomodel;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.LugarProyectos;
import sv.cruzroja.www.model.BeneficiadoModel;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class EstadisticasBean {

    private String parametro1;
    ProyectosBeneficiadomodel modelo = new ProyectosBeneficiadomodel();

    private int edad;
    private List<LugarproyectoEntity> lugarproyectoslist;
    private LugarProyectos model1 = new LugarProyectos();
    private BeneficiadoModel modelo3 = new BeneficiadoModel();
    private String idlugar;
    private int rango1;
    private int rango2;
    private boolean opcionswitch;
    private String proyectoid;
    private String departamentopara;
    private int muni;
    private String proyectoid2;
    private int hayentabla = 0;
    private List<String> columnaspivot;

    /**
     * Creates a new instance of EstadisticasBean
     */
//    public EstadisticasBean() {
//        createAnimatedModels();
//    }
    public void beneficiadosedadgenero() {

        //createAnimatedModels(this.getParametro1(), this.getEdad());
    }

//    private void createAnimatedModels() {
//        BarChartModel model = new BarChartModel();
//        List<BeneficiadosEntity> lista;
//        try {
//            lista = modelo.listarBeneficiadosbyidlugar("APP123", 80);
//            setAnimatedModel2(initBarModel(lista));
//        } catch (Exception e) {
//        } finally {
//        }
//
//        getAnimatedModel2().setTitle("Beneficiados");
//        getAnimatedModel2().setAnimate(true);
//        getAnimatedModel2().setLegendPosition("ne");
//        Axis yAxis = getAnimatedModel2().getAxis(AxisType.Y);
//        yAxis.setMin(0);
//        yAxis.setMax(100);
//    }
//
//    private BarChartModel initBarModel(List<BeneficiadosEntity> lista) {
//        BarChartModel model = new BarChartModel();
//        ChartSeries boys = new ChartSeries();
//        for (BeneficiadosEntity es : lista) {
//            boys.setLabel("Hombres");
//            boys.set(es.getIdbeneficiado().getGenero().getNombre(), es.getIdbeneficiado().getEdad());
//
//        }
//
//        model.addSeries(boys);
//
//        return model;
//    }
//
//    private void piedefault() {
//    }
    public void llenarcomboProyecto() {
        String usuarioactivo = null;
        int tipousuario = 0;
        try {
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            usuarioactivo = us.getIdusuario();
            tipousuario = us.getTipousuario().getIdtipou();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error interno ", "Al obtener session"));

        }
        System.out.print("llenarcombofuncion" + usuarioactivo + " LUGAR " + idlugar);
        if (tipousuario == 1) {
            lugarproyectoslist = model1.listarLugarproyectosporlugar(idlugar);
        } else {
            lugarproyectoslist = model1.listarLugarproyectosporlugarSegunEncargado(idlugar, usuarioactivo);
        }

    }

    public void pdfedadesogenero(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        String streamurl = "/reporte/generobene.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(model1.listarLugarproyectosporgeneropdf(proyectoid2)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void pdfedades(ActionEvent actionEvent) throws JRException, IOException {
        System.out.print("Imprimiendo rangos: " + rango1 + rango2);
        Map<String, Object> parametros = new HashMap<String, Object>();
        String streamurl = "/reporte/edadbene.jasper";
        System.out.print("El nombre del proyecto el codigo es: " + proyectoid);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo3.listarLugarproyectosporedad(rango1, rango2, proyectoid2)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void pdfasistenciaproyectos(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        String streamurl = "/reporte/asistenciaBene.jasper";
        System.out.print("El nombre del proyecto el codigo es: " + proyectoid);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo3.listapdfasistencia(proyectoid)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<BeneficiadosEntity> generarentabla() {
        try {
            List<BeneficiadosEntity> lista = modelo3.listaasistencia(proyectoid);
            if (lista.size() > 0) {
                hayentabla = 1;
                return lista;
            }
        } catch (Exception e) {
            hayentabla = 0;
        }
        return null;
    }

    //PIVOT MAESTRO ULTRA INSTINTO
    public List<Object[]> datospivot() {
        System.out.print("Iniciando...\n");
        try {
            if (proyectoid != null) {
                List<Object[]> columnaspivotOb;
                columnaspivotOb = modelo3.obteneractividades(proyectoid);
                System.out.print("TAMANOOOOOO: " + columnaspivotOb.size());
                this.columnaspivot = modelo3.obteneractividadesList(proyectoid);
                return modelo3.obteneractividadesdatos(columnaspivotOb, proyectoid);
            } else {
                return null;
            }

        } catch (Exception e) {
        }
        return null;
    }
    

    /**
     * @return the parametro1
     */
    public String getParametro1() {
        return parametro1;
    }

    /**
     * @param parametro1 the parametro1 to set
     */
    public void setParametro1(String parametro1) {
        this.parametro1 = parametro1;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the lugarproyectoslist
     */
    public List<LugarproyectoEntity> getLugarproyectoslist() {
        return lugarproyectoslist;
    }

    /**
     * @return the idlugar
     */
    public String getIdlugar() {
        return idlugar;
    }

    /**
     * @param idlugar the idlugar to set
     */
    public void setIdlugar(String idlugar) {
        this.idlugar = idlugar;
    }

    /**
     * @return the rango1
     */
    public int getRango1() {
        return rango1;
    }

    /**
     * @param rango1 the rango1 to set
     */
    public void setRango1(int rango1) {
        this.rango1 = rango1;
    }

    /**
     * @return the rango2
     */
    public int getRango2() {
        return rango2;
    }

    /**
     * @param rango2 the rango2 to set
     */
    public void setRango2(int rango2) {
        this.rango2 = rango2;
    }

    /**
     * @return the opcionswitch
     */
    public boolean isOpcionswitch() {
        return opcionswitch;
    }

    /**
     * @param opcionswitch the opcionswitch to set
     */
    public void setOpcionswitch(boolean opcionswitch) {
        this.opcionswitch = opcionswitch;
    }

    /**
     * @return the proyectoid
     */
    public String getProyectoid() {
        return proyectoid;
    }

    /**
     * @param proyectoid the proyectoid to set
     */
    public void setProyectoid(String proyectoid) {
        this.proyectoid = proyectoid;
    }

    /**
     * @return the departamentopara
     */
    public String getDepartamentopara() {
        return departamentopara;
    }

    /**
     * @param departamentopara the departamentopara to set
     */
    public void setDepartamentopara(String departamentopara) {
        this.departamentopara = departamentopara;
    }

    /**
     * @return the muni
     */
    public int getMuni() {
        return muni;
    }

    /**
     * @param muni the muni to set
     */
    public void setMuni(int muni) {
        this.muni = muni;
    }

    /**
     * @return the proyectoid2
     */
    public String getProyectoid2() {
        return proyectoid2;
    }

    /**
     * @param proyectoid2 the proyectoid2 to set
     */
    public void setProyectoid2(String proyectoid2) {
        this.proyectoid2 = proyectoid2;
    }

    /**
     * @return the hayentabla
     */
    public int getHayentabla() {
        return hayentabla;
    }

    /**
     * @param hayentabla the hayentabla to set
     */
    public void setHayentabla(int hayentabla) {
        this.hayentabla = hayentabla;
    }

    /**
     * @return the columnaspivot
     */
    public List<String> getColumnaspivot() {
        return columnaspivot;
    }

    /**
     * @param columnaspivot the columnaspivot to set
     */
    public void setColumnaspivot(List<String> columnaspivot) {
        this.columnaspivot = columnaspivot;
    }

}
