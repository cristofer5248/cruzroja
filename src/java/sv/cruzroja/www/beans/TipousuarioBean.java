/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.entities.TipouEntity;
import sv.cruzroja.www.model.TipousuarioModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class TipousuarioBean {

    private TipouEntity tipousuario;
    TipousuarioModel tipousuariomodel = new TipousuarioModel();

    /**
     * Creates a new instance of TipousuarioBean
     */
    public TipousuarioBean() {
        tipousuario = new TipouEntity();
    }

    public List<TipouEntity> getListaProyectos() {
        /* Notese que se llama al método listarEstudiantes
    para obtener la lista de objetos a partir de la bd */
        return tipousuariomodel.listarUsuarios();
    }

}
