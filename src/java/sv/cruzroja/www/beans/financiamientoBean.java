/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import sv.cruzroja.www.entities.FinanciamientoEntity;
import sv.cruzroja.www.model.financiamientoModel;
import sv.cruzroja.www.entities.ProyectosEntity;
import sv.cruzroja.www.entities.SociedadEntity;
import sv.cruzroja.www.utils.JsfUtil;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class financiamientoBean {

    private FinanciamientoEntity financiamiento;
    private List<FinanciamientoEntity> financiamientoList;
    private financiamientoModel model = new financiamientoModel();
    private Double totalfinanciamiento1;
    private Double totalfinanciamiento2;
    private Date fecha1;
    private Date fecha2;
    private Boolean opcion1;
    private Boolean opcion2;
    private String idgenerado;
    Calendar c1 = Calendar.getInstance();

    /**
     * Creates a new instance of financiamiento
     */
    public financiamientoBean() {
        financiamiento = new FinanciamientoEntity();
        financiamiento.setProyecto(new ProyectosEntity());
        financiamiento.setSociedadN(new SociedadEntity());
        listafinanciamiento();
        totalf();
    }

    public void listafinanciamiento() {
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int anio = Integer.parseInt(new SimpleDateFormat("YYYY").format(new Date()));

        financiamientoList = model.listarLugarMesactual(mes, anio);
        totalfinanciamiento2 = model.totalporfiltro(mes, anio);
    }

    public void totalf() {
        setTotalfinanciamiento1(model.total());
    }

    public void filtrarpdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fechainicio", "fechainicio");
        parametros.put("fecha2", "fecha");
        String streamurl = "/reporte/financiamiento_1.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(model.listarLugarFiltroPDF(fecha1, fecha2)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public String guardarFinanaciamiento() {
        if (opcion2 == true) {
            financiamiento.setFinanciamiento(financiamiento.getFinanciamiento() * -1);
        }
        if (model.insertarFinanciamiento(financiamiento) != 1) {
            model.modificarFinanciamiento(financiamiento);
            JsfUtil.setFlashMessage("exito", "Dato del financiamiento actualizado");
            return "financiamiento?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Dato del financiamiento registrado exitosamente");
//Forzando la redirección en el cliente
            return "financiamiento?faces-redirect=true";
        }
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        int dianumero = Integer.parseInt(dia);
        int mesnumero = Integer.parseInt(mes);
        if (dianumero < 10 && mesnumero < 10) {
            mes = "0" + String.valueOf(mesnumero);
        }

        setIdgenerado(financiamiento.getProyecto().getIdproyecto().substring(0, 2).toUpperCase().concat(dia).concat(mes));

        financiamiento.setIdp(getIdgenerado());
    }

    /**
     * @return the financiamiento
     */
    public FinanciamientoEntity getFinanciamiento() {
        return financiamiento;
    }

    /**
     * @param financiamiento the financiamiento to set
     */
    public void setFinanciamiento(FinanciamientoEntity financiamiento) {
        this.financiamiento = financiamiento;
    }

    /**
     * @return the financiamientoList
     */
    public List<FinanciamientoEntity> getFinanciamientoList() {
        try {
            if (this.getFecha1() != null && this.getFecha2() != null) {
                totalfinanciamiento2 = model.totalfiltro(fecha1, fecha2);
                return financiamientoList = model.listarLugarFiltro(fecha1, fecha2);
            }
        } catch (Exception e) {

        }

        return financiamientoList;
    }

    /**
     * @param financiamientoList the financiamientoList to set
     */
    public void setFinanciamientoList(List<FinanciamientoEntity> financiamientoList) {
        this.financiamientoList = financiamientoList;
    }

    /**
     * @return the totalfinanciamiento1
     */
    public Double getTotalfinanciamiento1() {
        return totalfinanciamiento1;
    }

    /**
     * @param totalfinanciamiento1 the totalfinanciamiento1 to set
     */
    public void setTotalfinanciamiento1(Double totalfinanciamiento1) {
        this.totalfinanciamiento1 = totalfinanciamiento1;
    }

    /**
     * @return the totalfinanciamiento2
     */
    public Double getTotalfinanciamiento2() {
        return totalfinanciamiento2;
    }

    /**
     * @param totalfinanciamiento2 the totalfinanciamiento2 to set
     */
    public void setTotalfinanciamiento2(Double totalfinanciamiento2) {
        this.totalfinanciamiento2 = totalfinanciamiento2;
    }

    /**
     * @return the fecha1
     */
    public Date getFecha1() {
        return fecha1;
    }

    /**
     * @param fecha1 the fecha1 to set
     */
    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    /**
     * @return the fecha2
     */
    public Date getFecha2() {
        return fecha2;
    }

    /**
     * @param fecha2 the fecha2 to set
     */
    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    /**
     * @return the opcion1
     */
    public Boolean getOpcion1() {
        return opcion1;
    }

    /**
     * @param opcion1 the opcion1 to set
     */
    public void setOpcion1(Boolean opcion1) {
        this.opcion1 = opcion1;
    }

    /**
     * @return the opcion2
     */
    public Boolean getOpcion2() {
        return opcion2;
    }

    /**
     * @param opcion2 the opcion2 to set
     */
    public void setOpcion2(Boolean opcion2) {
        this.opcion2 = opcion2;
    }

    /**
     * @return the idgenerado
     */
    public String getIdgenerado() {
        return idgenerado;
    }

    /**
     * @param idgenerado the idgenerado to set
     */
    public void setIdgenerado(String idgenerado) {
        this.idgenerado = idgenerado;
    }

}
