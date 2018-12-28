/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import sv.cruzroja.www.utils.JsfUtil;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.component.export.PDFOptions;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.entities.ProyectosEntity;
import sv.cruzroja.www.entities.LugarEntity;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.LugarProyectos;
import sv.cruzroja.www.model.ProyectosModel;
import sv.cruzroja.www.model.LugarModel;
import sv.cruzroja.www.model.UsuariosModel;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class LugarproyectoBean {

    private LugarproyectoEntity lugarproyecto;
    LugarProyectos model = new LugarProyectos();
    ProyectosModel model2 = new ProyectosModel();
    UsuariosModel model4 = new UsuariosModel();
    LugarModel model3 = new LugarModel();
    private List<LugarproyectoEntity> listaproyectos;
    private String idlugar;
    private String pruebawe;
    private String lugarid;
    private Date fecha1;
    private Date fecha2;
    Calendar c1 = Calendar.getInstance();
    private String encargado;
    private String hayono;
    private PDFOptions pdfOpt;
    private String param1;

    /**
     * Creates a new instance of Lugarproyecto
     */
    public LugarproyectoBean() {
        lugarproyecto = new LugarproyectoEntity();
        lugarproyecto.setIdlp(new ProyectosEntity());
        lugarproyecto.setIdlugar(new LugarEntity());
        listaproyectos = model.listarLugarproyectosporlugar("1");
    }
//kheee

    public List<LugarproyectoEntity> listalugarProyecto() {
        if (this.getFecha1() != null && this.getFecha2() != null) {
            return model.listarLugarproyectosporfecha(fecha1, fecha2);
        }
        return model.listarLugarproyectos();
    }

    public void handleKeyEvent() {

    }

    public void pdfproyectos(ActionEvent actionEvent) throws JRException, IOException {
        System.out.println("entre al metodo del pdf de lugarproyecto" + this.getFecha1());
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fechainicio", "fechainicio");
        parametros.put("fecha2", "fecha");
        String streamurl = "/reporte/proyectosporfecha.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(model.listarLugarproyectosporfechapdf(fecha1, fecha2)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public List<LugarproyectoEntity> getListalugarProyecto() {
        return model.listarLugarproyectos();
    }

    public String guardarLugarproyecto() {
        if (model.buscarDatoRepetido(pruebawe, lugarid)) {
            JsfUtil.setErrorMessage("Error", "Lugar y proyecto coinciden con un dato ya guardado");
            return "lugarproyecto?faces-redirect=false";
        }
        lugarproyecto.setEncargadoproyecto(model4.obtenerUsuario(encargado));
        lugarproyecto.setIdlp(model2.obtenerProyecto(pruebawe));
        lugarproyecto.setIdlugar(model3.obtenerLugar(lugarid));

        if (model.insertarLugarproyecto(lugarproyecto) != 1) {
            if (model.modificarLugarproyecto(lugarproyecto) == 0) {
                JsfUtil.setFlashMessage("exito", "Ese proyecto ya existe en ese lugar");
                return "lugarproyecto?faces-redirect=false";
            } else {
                JsfUtil.setFlashMessage("exito", "Lugar del proyecto actualizado");
            }

            return "lugarproyecto?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Lugar del proyecto registrado exitosamente");
//Forzando la redirección en el cliente
            return "lugarproyecto?faces-redirect=true";
        }
    }

    public String eliminarLugarproyecto() {
// Leyendo el parametro enviado desde la vista
        String carnet = this.param1;

        //System.out.println(carnet);
        if (model.eliminarLugarproyecto(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Lugar del proyecto eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este lugar que esta unido al proyecto");
        }
        return "lugarproyecto?faces-redirect=true";
    }

    public void generarId() {
        String idgenerado;
        try {
            idgenerado = lugarid.substring(0, 3).toUpperCase().concat(pruebawe.substring(0, 3).toUpperCase());
        } catch (Exception e) {
            String milisegundos = Integer.toString(c1.get(Calendar.MILLISECOND));
            System.out.println(milisegundos);
            idgenerado = pruebawe.substring(0, 3).concat(milisegundos);
        }
        lugarproyecto.setIdl(idgenerado);

    }

    public void obtenerLugar() {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        this.lugarproyecto = model.obtenerLugar(carnet);

    }

    public void selectidlugar() {

        try {
            listaproyectos = model.listarLugarproyectosporlugar(idlugar);
            if (listaproyectos.size() == 0) {
                System.out.print("LA LISTA ESTA VACIA");
                hayono = "No hay ningun proyectos en esa ubicacion";

            } else {
                hayono = "Correcto!";
            }
        } catch (Exception e) {

        }

    }

    public void selectidlugarEncargado() {
        String usuarioactivo = null;
        try {
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            usuarioactivo = us.getIdusuario();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error interno ", "Al obtener session"));

        }
        System.out.print("Se lleno???" + usuarioactivo);

        listaproyectos = model.listarLugarproyectosporlugarSegunEncargado(idlugar, usuarioactivo);
        if (listaproyectos.size() == 0) {
            hayono = "No hay ningun proyectos en esa ubicacion";
        } else {
            hayono = "Correcto!";
        }
    }

    /**
     * @return the lugarproyecto
     */
    public LugarproyectoEntity getLugarproyecto() {
        return lugarproyecto;
    }

    /**
     * @param lugarproyecto the lugarproyecto to set
     */
    public void setLugarproyecto(LugarproyectoEntity lugarproyecto) {
        this.lugarproyecto = lugarproyecto;
    }

    /**
     * @return the listaproyectos
     */
    public List<LugarproyectoEntity> getListaproyectos() {

        return listaproyectos;
    }

    /**
     * @param listaproyectos the listaproyectos to set
     */
    public void setListaproyectos(List<LugarproyectoEntity> listaproyectos) {
        this.listaproyectos = listaproyectos;
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
     * @return the pruebawe
     */
    public String getPruebawe() {
        return pruebawe;
    }

    /**
     * @param pruebawe the pruebawe to set
     */
    public void setPruebawe(String pruebawe) {
        this.pruebawe = pruebawe;
    }

    /**
     * @return the lugarid
     */
    public String getLugarid() {
        return lugarid;
    }

    /**
     * @param lugarid the lugarid to set
     */
    public void setLugarid(String lugarid) {
        this.lugarid = lugarid;
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
     * @return the encargado
     */
    public String getEncargado() {
        return encargado;
    }

    /**
     * @param encargado the encargado to set
     */
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    /**
     * @return the hayono
     */
    public String getHayono() {
        return hayono;
    }

    /**
     * @param hayono the hayono to set
     */
    public void setHayono(String hayono) {
        this.hayono = hayono;
    }
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "img" + File.separator + "logo.png";

        pdf.add(Image.getInstance(logo));
    }

    /**
     * @return the pdfOpt
     */
    public PDFOptions getPdfOpt() {
        return pdfOpt;
    }

    /**
     * @param pdfOpt the pdfOpt to set
     */
    public void setPdfOpt(PDFOptions pdfOpt) {
        this.pdfOpt = pdfOpt;
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
