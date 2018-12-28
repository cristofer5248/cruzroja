/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.entities.CategoriaEntity;
import sv.cruzroja.www.model.CategoriaModel;
import sv.cruzroja.www.model.LugarProyectos;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.model.BeneficiadoModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class CategoriaBean {

    private CategoriaModel categoriamodel = new CategoriaModel();
    private BeneficiadoModel benemodel = new BeneficiadoModel();
    private CategoriaEntity categoria;
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    private List<LugarproyectoEntity> lugarproyecto;
    private LugarProyectos lugarProyectosmodel = new LugarProyectos();
    private String param1;

    /**
     * Creates a new instance of CategoriaBean
     */
    public CategoriaBean() {
        categoria = new CategoriaEntity();
    }

    public List<CategoriaEntity> getListaCategoria() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return categoriamodel.listarCategoria();
    }

    public String guardarCategoria() {

        if (categoriamodel.insertarCategoria(categoria) != 1) {
            categoriamodel.modificarCategoria(categoria);
            JsfUtil.setFlashMessage("exito", "Lugar actualizado");
            return "areamisional?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "lugar registrado exitosamente");
//Forzando la redirección en el cliente
            return "areamisional?faces-redirect=true";
        }
    }

    public String eliminarArea() {
// Leyendo el parametro enviado desde la vista
        int codigo = Integer.parseInt(this.param1);

        //System.out.println(carnet);
        if (categoriamodel.eliminarCategoria(codigo) > 0) {
            JsfUtil.setFlashMessage("exito", "Lugar eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "areamisional?faces-redirect=true";
    }

    public void obtenerCategoria() {
        int carnet = Integer.parseInt(this.param1);
        this.categoria = categoriamodel.obtenerCategoria(carnet);

    }

    public void pdfareamisional(ActionEvent actionEvent) throws JRException, IOException {
        int codigo = 0;
        codigo = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));
        if (codigo != 0) {
            System.out.print("el codigo de categoria es : " + codigo);
        } else {
            System.out.print("No devuelvo nada we");
        }
        Map<String, Object> parametros = new HashMap<String, Object>();
        String streamurl = "/reporte/numerodebeneficiadosXarea.jasper";
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(streamurl));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(benemodel.listarcantidadeBeneficiadosXrea(codigo)));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * @return the categoria
     */
    public CategoriaEntity getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        idgenerado = categoria.getNombre().substring(0, 3).toUpperCase().concat(dia);
        //categoria.setIdcategoria(idgenerado);
    }

    public void proyectosporCategoria() {
        int codigo = 0;
        codigo = Integer.parseInt(this.param1);
        if (codigo != 0) {
            System.out.print("el codigo de categoria es : " + codigo);
            lugarproyecto = lugarProyectosmodel.listarLugarproyectosporArea(codigo);
        } else {
            System.out.print("No devuelvo nada we");
        }

    }

    /**
     * @return the lugarproyecto
     */
    public List<LugarproyectoEntity> getLugarproyecto() {
        return lugarproyecto;
    }

    /**
     * @param lugarproyecto the lugarproyecto to set
     */
    public void setLugarproyecto(List<LugarproyectoEntity> lugarproyecto) {
        this.lugarproyecto = lugarproyecto;
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
