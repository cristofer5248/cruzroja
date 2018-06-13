/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import sv.cruzroja.www.model.GeneroModel;
import sv.cruzroja.www.entities.GeneroEntity;


/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class GeneroBean {

    /**
     * Creates a new instance of GeneroBean
     */
   GeneroModel modelo = new GeneroModel();
    private GeneroEntity estudiante;
    

    public GeneroBean() {
        estudiante = new GeneroEntity();
    }

    public GeneroEntity getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(GeneroEntity estudiante) {
        this.estudiante = estudiante;
    }

    public List<GeneroEntity> getListaEstudiantes() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return modelo.listarLugar();
    }

    

    
}
