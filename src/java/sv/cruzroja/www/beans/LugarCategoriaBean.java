/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import javax.faces.bean.ViewScoped;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.entities.LugarcategoriaEntity;
import sv.cruzroja.www.model.CategoriaLugarModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class LugarCategoriaBean {

    private LugarcategoriaEntity lugarcategoria;
    CategoriaLugarModel categorialugarmodel = new CategoriaLugarModel();

    /**
     * Creates a new instance of LugarCategoriaBean
     */
    public LugarCategoriaBean() {
        lugarcategoria = new LugarcategoriaEntity();
    }

    public List<LugarcategoriaEntity> getLugarcategoria() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return categorialugarmodel.listarLugarcategoria();
    }

    public void obtenerLugarcategoria() {
        int carnet = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));        
        this.lugarcategoria = categorialugarmodel.obtenerLugarcategoria(carnet);

    }

    public String guardarLugar() {

        if (categorialugarmodel.insertarLugarcategoria(lugarcategoria) != 1) {
            categorialugarmodel.modificarLugarcategoria(lugarcategoria);
            JsfUtil.setFlashMessage("exito", "Lugar actualizado");
            return "lugarcategoria?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "lugar registrado exitosamente");
//Forzando la redirección en el cliente
            return "lugarcategoria?faces-redirect=true";
        }
    }

    public String eliminarLugar() {
// Leyendo el parametro enviado desde la vista
        int carnet = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));

        //System.out.println(carnet);
        if (categorialugarmodel.eliminarLugarcategoria(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Lugar eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "lugarcategoria?faces-redirect=true";
    }

    /**
     * @return the lugarcategoria
     */

    /**
     * @param lugarcategoria the lugarcategoria to set
     */
    public void setLugarcategoria(LugarcategoriaEntity lugarcategoria) {
        this.lugarcategoria = lugarcategoria;
    }

}
