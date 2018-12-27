/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.primefaces.event.FlowEvent;
import sv.cruzroja.www.entities.Actividades;
import sv.cruzroja.www.entities.ActividadesdetallesEntity;
import sv.cruzroja.www.entities.LugarEntity;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.ActividadesModel;
import sv.cruzroja.www.model.LugarProyectos;
import sv.cruzroja.www.model.LugarModel;
import sv.cruzroja.www.model.ActividadesDetaModel;
import sv.cruzroja.www.utils.JsfUtil;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class ActividadesBean implements Serializable {

    ActividadesModel modelo = new ActividadesModel();
    ActividadesDetaModel modelo4 = new ActividadesDetaModel();
    private Actividades actividades;
//    private ActividadesdetallesEntity actividadesDetalles;
    private String proyectoid;
    private List<LugarproyectoEntity> listaproyectos;
    private List<Actividades> actividadesmodal;

    LugarProyectos modelo2 = new LugarProyectos();
    LugarModel modelo3 = new LugarModel();
    private String idlugar;
    private boolean skip;
    private boolean skip2 = false;
    private String datoscompletos;
    private String hayono;
    private boolean skip3;
    private String param1;
    private String idactividad;

    Calendar c1 = Calendar.getInstance();

    public ActividadesBean() {
        actividades = new Actividades();
//        actividadesDetalles = new ActividadesdetallesEntity();

    }

    public void selectidlugar() {
        try {
            this.setListaproyectos(modelo2.listarLugarproyectosporlugar(idlugar));
            if (getListaproyectos().size() == 0) {
                System.out.print("LA LISTA ESTA VACIA");
                hayono = "No hay ningun proyectos en esa ubicacion";
                skip2 = false;
                datoscompletos = "Datos Incompletos";
                this.setListaproyectos(null);
            } else {
                hayono = "Correcto!";
                skip2 = true;

            }
        } catch (Exception e) {

        }
    }

    public String integraractividad() {
        String codigo = JsfUtil.getRequest().getParameter("tipo");
        actividades.setLugarproyectoPadre(new LugarproyectoEntity(proyectoid));
        actividades.setDetalleactividad(new ActividadesdetallesEntity(idactividad));
        int idgenerado = 0;
        try {
//            idgenerado = (c1.get(Calendar.MILLISECOND));
            boolean repetido = modelo.buscarRepetidoIdyPadre(idactividad, proyectoid);
            if (repetido == false) {
//        actividades.setIdactividad(idgenerado);
                if (modelo.insertarActividad(actividades) != 0) {
                    JsfUtil.setFlashMessage("exito", "Actividad integrada o actualizada");
                    if (codigo.equals("T")) {
                        return "ActividadesTec?faces-redirect=true";
                    } else {
                        return "Actividades?faces-redirect=true";
                    }

                }
            } else {
                JsfUtil.setErrorMessage(null, "Actividad ya integrada o error interno");
                if (codigo.equals("T")) {
                    return "ActividadesTec?faces-redirect=false";
                }

                return "Actividades?faces-redirect=false";
            }
        } catch (Exception e) {

        }
        JsfUtil.setErrorMessage(null, "No se puede integrar esa actividad porque ya se encuentra integrada en el proyecto selecionado o posiblemente se encontro un error interno");
        return "Actividades?faces-redirect=false";
    }

    public List<LugarproyectoEntity> retornarListaLugarproyectoPadre() {

        try {
            if (idlugar != null) {
                hayono = "Correcto!";
                List<LugarproyectoEntity> datitos = modelo2.listarLugarproyectosporlugar(idlugar);
                if (datitos.size() > 0) {
                    skip3 = true;
                } else {
                    skip3 = false;
                }

                return datitos;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        hayono = "No hay ningun proyectos en esa ubicacion";
        return null;
    }

    public List<LugarproyectoEntity> retornarListaLugarproyectoPadreParaTecnico() {
        String usuarioactivo = null;
        try {
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            usuarioactivo = us.getIdusuario();
            if (idlugar != null) {
                hayono = "Correcto!";
                List<LugarproyectoEntity> datitos = modelo2.listarLugarproyectosporlugarSegunEncargado(idlugar, usuarioactivo);
                if (datitos.size() > 0) {
                    skip3 = true;
                } else {
                    skip3 = false;
                }

                return datitos;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error interno ", "Al obtener session"));
            e.printStackTrace();
        }
        System.out.print("Se lleno???" + usuarioactivo);
        hayono = "No hay ningun proyectos en esa ubicacion";
        return null;
    }

    public String integraractidadaproyecto() {
        actividades.setLugarproyectoPadre(new LugarproyectoEntity(proyectoid));
        String codigo = JsfUtil.getRequest().getParameter("tipo");
        System.out.print("Me desesperooooo el tipo es: " + codigo);
        int bandera1 = modelo.insertarActividad(actividades);
        if (bandera1 == 0) {
            modelo.actualizarActividad(actividades);
            JsfUtil.setFlashMessage("exito", "Los datos de la actividad fueron actualizados");
            if (codigo.equals("T")) {
                return "ActividadesTec?faces-redirect=true";
            } else {
                return "Actividades?faces-redirect=true";
            }

        } else {
            JsfUtil.setFlashMessage("exito", "Actividad guardada correctamente");
            if (codigo.equals("T")) {
                return "ActividadesTec?faces-redirect=true";
            }
            return "Actividades?faces-redirect=true";
        }
    }

    public String guardar() {

        String codigo = JsfUtil.getRequest().getParameter("tipo");
        System.out.print("Me desesperooooo el tipo es: " + codigo);
        int bandera1 = modelo.insertarActividad(actividades);
        if (bandera1 == 0) {
            modelo.actualizarActividad(actividades);
            JsfUtil.setFlashMessage("exito", "Actividad integrada con exito");
            if (codigo.equals("T")) {
                return "ActividadesTec?faces-redirect=true";
            } else {
                return "Actividades?faces-redirect=true";
            }

        } else {
            JsfUtil.setFlashMessage("exito", "Actividad guardada correctamente");
            if (codigo.equals("T")) {
                return "ActividadesTec?faces-redirect=true";
            }
            return "Actividades?faces-redirect=true";
        }

    }

    public String eliminar() {
        String codigo = this.param1;

        if (modelo4.eliminarActividadDet(codigo) != 0) {
            JsfUtil.setFlashMessage("exito", "Actividad eliminad"
                    + "a exitosamente ");
            return "Actividades?faces-redirect=true";
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a esta actividad posiblemente este asosciada a un beneficiado");
        }
        return "Actividades?faces-redirect=false";
    }

    public void generarid() {
        String idgenerado;
        try {
            String milisegundos = Integer.toString(c1.get(Calendar.MILLISECOND));
            idgenerado = idlugar.substring(0, 2).toUpperCase().concat(actividades.getDetalleactividad().getDetalle().substring(0, 2).toUpperCase()).concat(milisegundos.substring(0, 2));
//            idgenerado = idgenerado.concat(actividades.getTitulo().substring(0, 2).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            String milisegundos = Integer.toString(c1.get(Calendar.MILLISECOND));
            System.out.println(milisegundos);
            idgenerado = idlugar.substring(0, 3).concat(milisegundos);
        }
//        actividades.setIdactividad(idgenerado);
    }

//    public void obtenerActividad() {
//        String carnet = JsfUtil.getRequest().getParameter("codigo");
//        System.out.print("ESTE ES EL CODIGO RECOGIDO PARA MODIFICAR "+carnet);
//        this.actividadesDetalles = modelo4.obtenerActividadporId(carnet);
//    }
    public void llenarIntegraractividad() {
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        idactividad = codigo;

    }

    public void llenarproyectoid() {
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        System.out.print("sdfnsfusfuisfhysdfsdhfsdhsdfs " + codigo);
        proyectoid = codigo;

    }

    public void selectidlugarEncargado2() {
        String usuarioactivo = null;
        try {
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            usuarioactivo = us.getIdusuario();
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error interno ", "Al obtener session"));

        }
        System.out.print("Se lleno???" + usuarioactivo);

        this.setListaproyectos(modelo2.listarLugarproyectosporlugarSegunEncargado(idlugar, usuarioactivo));
        if (getListaproyectos().size() == 0) {
            hayono = "No hay ningun proyectos en esa ubicacion";
        } else {
            hayono = "Correcto!";
        }

    }

    public List<LugarEntity> getListaLugar() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return modelo3.listarLugar();
    }

    public List<Actividades> getListarTodasLasActividades() {
        return modelo.listarActividades();
    }

    public void prueba() {
        System.out.print("Ya me desespereeeeeeeeeeeeee");
    }

    public List<Actividades> getListarActividadesXproyecto() {

//        System.out.print("El codigo del proyecto para actividad es: " + proyectoid);
        try {
            if (proyectoid != null) {
                System.out.print("El codigo del proyecto para actividad es: " + proyectoid);
                return modelo.listarActividadesXproyecto(proyectoid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void listarActividadesXIddetalleActividad() {
        String codigo = this.param1;
//        System.out.print("El codigo del proyecto para actividad es: " + proyectoid);
        try {
            if (codigo != null) {
                System.out.print("El codigo del proyecto para actividad es: " + codigo);
                setActividadesmodal(modelo.listarActividadesXidDetalle(codigo));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void pdfproyectos(ActionEvent actionEvent) throws JRException, IOException {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codbene", "ADD123");
        String streamurl = "/reporte/actividadesProyectos.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo.proyectosPDF(carnet)));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=fReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void excelproyectos(ActionEvent actionEvent) throws JRException, IOException {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codbene", "ADD123");
        String streamurl = "/reporte/actividadesProyectos.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(modelo.proyectosPDF(carnet)));

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=fReporte.xls");
        ServletOutputStream stream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    /**
     * @return the actividades
     */
    public Actividades getActividades() {
        return actividades;
    }

    /**
     * @param actividades the actividades to set
     */
    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
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

    public String onFlowProcess(FlowEvent event) {

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
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

    /**
     * @return the idactividad
     */
    public String getIdactividad() {
        return idactividad;
    }

    /**
     * @param idactividad the idactividad to set
     */
    public void setIdactividad(String idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * @return the skip2
     */
    public boolean isSkip2() {
        return skip2;
    }

    /**
     * @param skip2 the skip2 to set
     */
    public void setSkip2(boolean skip2) {
        this.skip2 = skip2;
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
     * @return the datoscompletos
     */
    public String getDatoscompletos() {

        return datoscompletos;
    }

    /**
     * @param datoscompletos the datoscompletos to set
     */
    public void setDatoscompletos(String datoscompletos) {
        this.datoscompletos = datoscompletos;
    }

    public void recolectardatos() {
        datoscompletos = null;
        try {
            datoscompletos = modelo2.obtenerLugarString(proyectoid);
            if (datoscompletos == null) {
                datoscompletos = "Error, datos no recolectados";
            }
        } catch (Exception e) {
        }

    }

    /**
     * @return the skip3
     */
    public boolean isSkip3() {
        return skip3;
    }

    /**
     * @param skip3 the skip3 to set
     */
    public void setSkip3(boolean skip3) {
        this.skip3 = skip3;
    }

    /**
     * @return the actividadesmodal
     */
    public List<Actividades> getActividadesmodal() {
        return actividadesmodal;
    }

    /**
     * @param actividadesmodal the actividadesmodal to set
     */
    public void setActividadesmodal(List<Actividades> actividadesmodal) {
        this.actividadesmodal = actividadesmodal;
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
