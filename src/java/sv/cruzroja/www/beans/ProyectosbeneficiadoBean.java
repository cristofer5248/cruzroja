/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.util.JRLoader;
import sv.cruzroja.www.entities.BeneficiadosEntity;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sv.cruzroja.www.entities.Actividades;
import sv.cruzroja.www.entities.DatosbeneficiadosEntity;
import sv.cruzroja.www.model.ProyectosBeneficiadomodel;
import sv.cruzroja.www.model.ActividadesDetaModel;
import sv.cruzroja.www.model.ActividadesModel;

import sv.cruzroja.www.utils.JsfUtil;

/**
 *
 * @author crist
 */
@ManagedBean
@SessionScoped
public class ProyectosbeneficiadoBean {

    private static final long serialVersionUID = -1;
    private BeneficiadosEntity beneficiados;
    ActividadesModel actividadesmodel = new ActividadesModel();
    ProyectosBeneficiadomodel beneficiadosmodel = new ProyectosBeneficiadomodel();
    ActividadesDetaModel modelo2 = new ActividadesDetaModel();
    private List<BeneficiadosEntity> beneficiadosselected;
    private List<BeneficiadosEntity> beneficiadoproyecto;
    public String idfinal;
    private String idbene;
    private String proyectoid;
    private Date fecha1;
    private Date fecha2;
    private int idactividad;
    private String param1;
    Calendar c1 = Calendar.getInstance();

    /**
     * Creates a new instance of ProyectosbeneficiadoBean
     */
    public ProyectosbeneficiadoBean() {

        beneficiados = new BeneficiadosEntity();
        beneficiados.setIdbeneficiado(new DatosbeneficiadosEntity());
        beneficiados.setIdproyecto(new Actividades());

    }

    public List<BeneficiadosEntity> getListaBeneficiados() {
        /* Notese que se llama al método listarEstudiantes
        para obtener la lista de objetos a partir de la bd */
        return beneficiadosmodel.listarBeneficiados();
    }

    public void ya() {
        System.out.print("dsfsfsfsfnsdhfshfusfsjfbsfhsdyfbsdfnsdunfsdufn");
    }

    public void pdfbeneiciados(ActionEvent actionEvent) throws JRException, IOException {
        System.out.println("entre al metodo del pdf de lugarproyecto" + this.getFecha1());
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fechainicio", "fechainicio");
        parametros.put("fecha2", "fecha");
        String streamurl = "/reporte/proyectosporfecha.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(beneficiadosmodel.listarLugarproyectosporfechapdf(fecha1, fecha2)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void guardarBeneficiadoProyecto() {
        String codigo = JsfUtil.getRequest().getParameter("tipo");

        try {
            int repetido = 0;
            repetido = beneficiadosmodel.buscarXidbeneYactividad(idbene, idactividad);
            if (repetido != 0) {
                JsfUtil.setFlashMessage("exito", "Beneficiado ya integrado a dicha actividad");
//                return "registroBeneficiado?faces-redirect=true";
            }
            List<Actividades> nombreActividades = actividadesmodel.listarActividadesXid(idactividad);

//        System.out.print("TITULOOOOOOOOOO "+tituloactividad);
            codigo = JsfUtil.getRequest().getParameter("tipo");
            beneficiados.setIdbeneficiado(new DatosbeneficiadosEntity(idbene));
            beneficiados.setIdproyecto(new Actividades(idactividad));
            String idbenepro;
            idbenepro = idbene.substring(0, 2).concat((nombreActividades.get(0).getDetalleactividad().getTitulo()).substring(2, 4)).toUpperCase();
            idbenepro = idbenepro + (nombreActividades.get(0).getLugarproyectoPadre().getIdlp().getIdproyecto().substring(0, 2)).toUpperCase();
            Calendar c2 = Calendar.getInstance();
            //aqui los mimislsesgsggsg
            String milisegundos = Integer.toString(c2.get(Calendar.MILLISECOND));
            idbenepro = idbenepro.concat(milisegundos.substring(0, 2));

            beneficiados.setIdb(idbenepro);

            System.out.print("El tipo de cliente " + codigo);
            if (beneficiadosmodel.insertarBeneficiadoProyecto(beneficiados) != 1) {
                beneficiadosmodel.modificarBeneficiadoProyecto(beneficiados);
                JsfUtil.setFlashMessage("exito", "Fue actualizado el proyecto al que esta unido el beneficiado");
                if (codigo.equals("T")) {

//                    return "registroBeneficiadoTec?faces-redirect=true";
                } else {
                    JsfUtil.setFlashMessage("exito", "Beneficiado añadido a proyecto exitosamente");

                }
            }
            if (codigo.equals("T")) {
                JsfUtil.setFlashMessage("exito", "Beneficiado añadido a proyecto exitosamente");
//                return "registroBeneficiadoTec?faces-redirect=true";
            }

        } catch (Exception e) {
//            e.printStackTrace();
        }
        JsfUtil.setFlashMessage("exito", "Beneficiado añadido a proyecto exitosamente");
//        return "registroBeneficiado?faces-redirect=true";
    }

    public void pdfbene(ActionEvent actionEvent) throws JRException, IOException {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
//        System.out.print("carneeeeeeeeeeeeeet es esete:" + carnet);
        Map<String, Object> parametros = new HashMap<String, Object>();
//        parametros.put("codbene", "ADD123");
        String streamurl = "/reporte/proyectoBeneficiado.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(beneficiadosmodel.listanativa1(carnet)));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public String eliminarBeneficidosproyecto() {
// Leyendo el parametro enviado desde la vista
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        System.out.println(carnet);

        //System.out.println(carnet);
        if (beneficiadosmodel.eliminar(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Dato eliminado eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a registro");
        }
        return "registroBeneficiado?faces-redirect=true";
    }

    public String meterbeneficiadoid() {
        try {
// Leyendo el parametro enviado desde la vista
            String carnet = JsfUtil.getRequest().getParameter("codigo");
            System.out.println("el codigo es" + carnet);
            beneficiadoproyecto = beneficiadosmodel.listarBeneficiadosbyidbene(carnet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "beneficiadoproyecto?faces-redirect=true";
    }

    public String meterbeneficiadoidtec() {
// Leyendo el parametro enviado desde la vista
        String carnet = this.param1;
        System.out.println("el codigo es" + carnet);
        beneficiadoproyecto = beneficiadosmodel.listarBeneficiadosbyidbene(carnet);

        return "beneficiadoproyectoTec?faces-redirect=true";
    }

    public void genearid() {
        beneficiados.setIdbeneficiado(new DatosbeneficiadosEntity());
        //beneficiados.setIdb(idgenerado);
    }

    public void llenaridbeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        idbene = codigo;
    }

    /**
     * @return the beneficiados
     */
    public BeneficiadosEntity getBeneficiados() {
        return beneficiados;
    }

    /**
     * @param beneficiados the beneficiados to set
     */
    public void setBeneficiados(BeneficiadosEntity beneficiados) {
        this.beneficiados = beneficiados;
    }

    /**
     * @return the beneficiadosselected
     */
    public List<BeneficiadosEntity> getBeneficiadosselected() {
        return beneficiadosselected;
    }

    /**
     * @param beneficiadosselected the beneficiadosselected to set
     */
    public void setBeneficiadosselected(List<BeneficiadosEntity> beneficiadosselected) {
        this.beneficiadosselected = beneficiadosselected;
    }

    /**
     * @return the idfinal
     */
    public String getIdfinal() {
        return idfinal;
    }

    /**
     * @param idfinal the idfinal to set
     */
    public void setIdfinal(String idfinal) {
        this.idfinal = idfinal;
    }

    /**
     * @return the beneficiadoproyecto
     */
    public List<BeneficiadosEntity> getBeneficiadoproyecto() {
        return beneficiadoproyecto;
    }

    /**
     * @param beneficiadoproyecto the beneficiadoproyecto to set
     */
    public void setBeneficiadoproyecto(List<BeneficiadosEntity> beneficiadoproyecto) {
        this.beneficiadoproyecto = beneficiadoproyecto;
    }

    /**
     * @return the idbene
     */
    public String getIdbene() {
        return idbene;
    }

    /**
     * @param idbene the idbene to set
     */
    public void setIdbene(String idbene) {
        this.idbene = idbene;
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
     * @return the idactividad
     */
    public int getIdactividad() {
        return idactividad;
    }

    /**
     * @param idactividad the idactividad to set
     */
    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * @return the param1
     */
    public String getParam1() {
        return param1;
    }

    /**
     * @param param1 the param1 to set
     */
    public void setParam1(String param1) {
        this.param1 = param1;
    }

}
