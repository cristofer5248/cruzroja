/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import sv.cruzroja.www.entities.DatosbeneficiadosEntity;
import sv.cruzroja.www.entities.GeneroEntity;
import sv.cruzroja.www.model.BeneficiadoModel;
import sv.cruzroja.www.model.ProyectosBeneficiadomodel;
import sv.cruzroja.www.utils.JsfUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import sv.cruzroja.www.entities.ConsolidadoTable;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class BeneficiadoBeans {

    BeneficiadoModel modelo = new BeneficiadoModel();
    ProyectosBeneficiadomodel modelo2 = new ProyectosBeneficiadomodel();
    ProyectosbeneficiadoBean beneficiado2;
    private DatosbeneficiadosEntity beneficiado;
    private List<DatosbeneficiadosEntity> beneficiadolist;
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    public String apellidos;
    private String param1;
    private Date fecha1;
    private Date fecha2;
//    private List<String> consolidatedTable;

    public BeneficiadoBeans() {
        beneficiado = new DatosbeneficiadosEntity();
        beneficiado.setGenero(new GeneroEntity());

    }

    public DatosbeneficiadosEntity getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(DatosbeneficiadosEntity beneficiado) {
        this.beneficiado = beneficiado;
    }

    public void listaEstudiantes() {
        try {
            if (this.apellidos != null) {
                this.beneficiadolist = modelo.buscarXapellidos(apellidos);
            } else {
                Date date;
                Calendar cal2 = Calendar.getInstance();
                String year = String.valueOf(cal2.get(Calendar.YEAR));
                int month = cal2.get(Calendar.MONTH);
                month = month - 2;
                String mesString = String.valueOf(month);
                String fechamandar = year + "-" + mesString + "-" + "1";
                System.out.print(fechamandar);
                this.beneficiadolist = modelo.listarLugarX2meses(fechamandar);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }

    public void obtenerBeneficiado() {
        try {
            String codigo = this.param1;
            System.out.print("QUE ME SALIOOOO " + codigo);
            this.beneficiado = modelo.obtenerBeneficiado(codigo);
            JsfUtil.setFlashMessage("Listo, abre el formulario de registro", "Listo, abre el formulario de registro");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void busquedaporFecha(ActionEvent actionEvent) throws JRException, IOException {
        try {

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechainicio", "fechainicio");
            parametros.put("fecha2", "fecha");
            String streamurl = "/reporte/rastreandoBeneficiados.jasper";
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo.rastreandoBeneficiados(fecha1, fecha2)));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

            stream.flush();
            stream.close();

            FacesContext.getCurrentInstance().responseComplete();
//            System.out.print("Vaya ahorita iniciamos el experimento we");
//            int aver = modelo.rastreandoBeneficiados(this.fecha1, this.fecha2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void busquedaporFechaExcel(ActionEvent actionEvent) throws JRException, IOException {
        try {

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fechainicio", "fechainicio");
            parametros.put("fecha2", "fecha");
            String streamurl = "/reporte/rastreandoBeneficiadosexcel.jasper";
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo.rastreandoBeneficiados(fecha1, fecha2)));
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte.xls");
            ServletOutputStream stream = response.getOutputStream();
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
            exporter.exportReport();
            stream.flush();
            stream.close();

            FacesContext.getCurrentInstance().responseComplete();
//            System.out.print("Vaya ahorita iniciamos el experimento we");
//            int aver = modelo.rastreandoBeneficiados(this.fecha1, this.fecha2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarBeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("tipo");
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        String year = Integer.toString(c1.get(Calendar.YEAR));
        String hora = Integer.toString((c1.get(Calendar.HOUR))).concat(Integer.toString(c1.get(Calendar.MINUTE)));

        System.out.print("codigoooo " + codigo);
        if (modelo.insertarEstudiante(beneficiado) != 1) {
            modelo.modificarBeneficiado(beneficiado);
            JsfUtil.setFlashMessage("exito", "El dato del beneficiado fue actualizado");
            if (codigo == "T") {

            }

        } else {
            JsfUtil.setFlashMessage("exito", "Beneficiario registrado exitosamente");
            //Forzando la redirección en el cliente
            if (codigo == "T") {

            }

        }

    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        int dianumero = Integer.parseInt(dia);
        int mesnumero = Integer.parseInt(mes);
        if (dianumero < 10 && mesnumero < 10) {
            mes = "0" + String.valueOf(mesnumero);
        } else {
            if (mesnumero == 10) {
                mes = "O";
            }
            if (mesnumero == 11) {
                mes = "N";
            }
            if (mesnumero == 12) {
                mes = "D";
            }
        }

        idgenerado = beneficiado.getNombres().substring(0, 3).toUpperCase().concat(beneficiado.getApellidos().substring(0, 2)).toUpperCase().concat(dia).concat(mes);

        beneficiado.setIdusuario(idgenerado);
    }

    public String eliminarBeneficiado() {
// Leyendo el parametro enviado desde la vista
        String carnet = this.param1;
        //System.out.println(carnet);
        if (modelo.eliminarLugar(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Beneficiado eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este beneficiado");
        }
        return "registroBeneficiado?faces-redirect=true";
    }

    public List<ConsolidadoTable> consolidated() {

        try {
            if (fecha1 != null && fecha2 != null) {
                String fechaNformat = new SimpleDateFormat("yyyy-MM-dd").format(fecha1);
                String fechaNformat2 = new SimpleDateFormat("yyyy-MM-dd").format(fecha2);
                List<Object[]> r = (List<Object[]>) modelo.nativo(fechaNformat, fechaNformat2);
                List<ConsolidadoTable> consolidedLis = new ArrayList<ConsolidadoTable>(r.size());
                for (Object[] o : r) {
                    ConsolidadoTable b = new ConsolidadoTable();
                    b.setRangoEdades((String) o[0]);
                    b.setMasculino(((Long) o[1]).intValue());
                    b.setFemenino(((Long) o[2]).intValue());
                    consolidedLis.add(b);
                }
                return consolidedLis;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the beneficiadolist
     */
    public List<DatosbeneficiadosEntity> getBeneficiadolist() {
        return beneficiadolist;
    }

    /**
     * @param beneficiadolist the beneficiadolist to set
     */
    public void setBeneficiadolist(List<DatosbeneficiadosEntity> beneficiadolist) {
        this.beneficiadolist = beneficiadolist;
    }

    /**
     * @return the fordeleting
     */
    public String getParam1() {
        return param1;
    }

    /**
     * @param fordeleting the fordeleting to set
     */
    public void setParam1(String param1) {
        this.param1 = param1;
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

}
