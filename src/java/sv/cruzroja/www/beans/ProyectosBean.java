/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.cruzroja.www.entities.ProyectosEntity;
import sv.cruzroja.www.entities.CategoriaEntity;
import sv.cruzroja.www.entities.SociedadEntity;
import sv.cruzroja.www.entities.MunicipioEntity;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.ProyectosModel;
import sv.cruzroja.www.model.CategoriaModel;
import sv.cruzroja.www.model.SociedadModel;
import sv.cruzroja.www.model.MunicipioModel;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.model.DepartamentoModel;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class ProyectosBean {

    ProyectosModel proyectomodel = new ProyectosModel();
    CategoriaModel categoria = new CategoriaModel();
    SociedadModel sociedad = new SociedadModel();
    MunicipioModel municipio = new MunicipioModel();
    DepartamentoModel departamentomodel = new DepartamentoModel();
    private ProyectosEntity proyecto;
    private List<MunicipioEntity> muni = new ArrayList<MunicipioEntity>();
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    private int departamento;
    private String param1;

    /**
     * Creates a new instance of ProyectosBean
     */
    public ProyectosBean() {
        proyecto = new ProyectosEntity();
        proyecto.setCategoria(new CategoriaEntity());
        proyecto.setSociedadN(new SociedadEntity());
        proyecto.setSeccionales(new MunicipioEntity());
        proyecto.setEncargado(new UsuariosEntity());
        muni = municipio.obtenerMunicipio(departamentomodel.listarByIdDepartamento(1));

    }

    public List<ProyectosEntity> getListaProyectos() {
        /* Notese que se llama al método listarEstudiantes
    para obtener la lista de objetos a partir de la bd */
        return proyectomodel.listarProyectos();
    }

    public String eliminarLugar() {
// Leyendo el parametro enviado desde la vista
        String codigo = this.param1;

        //System.out.println(carnet);
        if (proyectomodel.eliminarProyecto(codigo) > 0) {
            JsfUtil.setFlashMessage("exito", "Lugar eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "proyectos?faces-redirect=true";
    }

    public void obtenerProyecto() {
        try {
            String carnet = this.param1;
            this.proyecto = proyectomodel.obtenerProyecto(carnet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String guardarLugar() {

        if (proyectomodel.insertarProyecto(proyecto) != 1) {
            proyectomodel.modificarProyecto(proyecto);
            JsfUtil.setFlashMessage("exito", "Lugar actualizado");
            return "proyectos?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "lugar registrado exitosamente");
//Forzando la redirección en el cliente
            return "proyectos?faces-redirect=true";
        }
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        int diaint = Integer.valueOf(dia);
        int mesint = Integer.valueOf(mes);
//        String depaletter= proyecto.getSeccionales().getNombre().substring(0,1);
        if ((diaint < 10) && (mesint < 10)) {
            mes = "0" + mes;

        } else {

            if (mesint == 10) {
                mes = "O";
            }
            if (mesint == 11) {
                mes = "N";
            }
            if (mesint == 12) {
                mes = "D";
            }
        }
        idgenerado = proyecto.getNombre().substring(0, 3).toUpperCase().concat(dia).concat(mes);
        proyecto.setIdproyecto(idgenerado);
    }

    public void handleValueChange() {
        System.out.println(getDepartamento());
        setMuni(municipio.obtenerMunicipio(departamentomodel.listarByIdDepartamento(getDepartamento())));
    }

    /**
     * @return the proyecto
     */
    public ProyectosEntity getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(ProyectosEntity proyecto) {
        this.proyecto = proyecto;
    }

    /**
     * @return the muni
     */
    public List<MunicipioEntity> getMuni() {
        return muni;
    }

    /**
     * @param muni the muni to set
     */
    public void setMuni(List<MunicipioEntity> muni) {
        this.muni = muni;
    }

    /**
     * @return the departamento
     */
    public int getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
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
