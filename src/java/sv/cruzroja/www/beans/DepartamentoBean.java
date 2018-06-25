/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.entities.Actividades;
import sv.cruzroja.www.entities.DepartamentoEntity;
import sv.cruzroja.www.model.DepartamentoModel;
import sv.cruzroja.www.model.LugarProyectos;
import sv.cruzroja.www.entities.LugarproyectoEntity;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.model.ActividadesModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class DepartamentoBean {

    DepartamentoModel modelo = new DepartamentoModel();
    LugarProyectos model2 = new LugarProyectos();
    ActividadesModel model3 = new ActividadesModel();
    private List<LugarproyectoEntity> proyectosDepartamento;
    private List<Actividades> actividadesDepartamento;
    private DepartamentoEntity departamento;

    /**
     * Creates a new instance of DepartamentoBean
     */
    public DepartamentoBean() {
        departamento = new DepartamentoEntity();
    }

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoEntity departamento) {
        this.departamento = departamento;
    }

    public void buscarproyectos() {
        int codigo = 0;
        codigo = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));
        if (codigo != 0) {
            setProyectosDepartamento(model2.listarLugarproyectosporDepartamento(codigo));
        } else {
            System.out.print("No devolvio nada we");
        }
    }

    public void buscarActividades() {
        int codigo = 0;
        codigo = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));
        if (codigo != 0) {
            setActividadesDepartamento(model3.listarActividadesporDepartamento(codigo));
        } else {
            System.out.print("No devolvio nada we");
        }
    }

    public void guardar() {
        try {
            modelo.insertardepartamento(departamento);
        } catch (Exception e) {
        }

    }

    public List<DepartamentoEntity> getListaDepartamento() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return modelo.listarDepartamento();
    }

    /**
     * @return the proyectosDepartamento
     */
    public List<LugarproyectoEntity> getProyectosDepartamento() {
        return proyectosDepartamento;
    }

    /**
     * @param proyectosDepartamento the proyectosDepartamento to set
     */
    public void setProyectosDepartamento(List<LugarproyectoEntity> proyectosDepartamento) {
        this.proyectosDepartamento = proyectosDepartamento;
    }

    /**
     * @return the actividadesDepartamento
     */
    public List<Actividades> getActividadesDepartamento() {
        return actividadesDepartamento;
    }

    /**
     * @param actividadesDepartamento the actividadesDepartamento to set
     */
    public void setActividadesDepartamento(List<Actividades> actividadesDepartamento) {
        this.actividadesDepartamento = actividadesDepartamento;
    }

}
