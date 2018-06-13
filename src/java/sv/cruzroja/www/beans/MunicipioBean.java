/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.entities.MunicipioEntity;
import sv.cruzroja.www.model.MunicipioModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class MunicipioBean {

    MunicipioModel modelo = new MunicipioModel();
    private MunicipioEntity municipio;

    /**
     * Creates a new instance of MunicipioBean
     */
    public MunicipioBean() {
        municipio = new MunicipioEntity();
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MunicipioEntity municipio) {
        this.municipio = municipio;
    }

    public List<MunicipioEntity> getListaMunicipio() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return modelo.listarMunicipio();
    }

}
