/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;


import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.entities.SociedadEntity;
import sv.cruzroja.www.model.SociedadModel;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class SociedadBean {

    SociedadModel sociedadmodel = new SociedadModel();
    private SociedadEntity sociedad;
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;

    /**
     * Creates a new instance of SociedadBean
     */
    public SociedadBean() {
        sociedad = new SociedadEntity();
    }

    public List<SociedadEntity> getListaSociedad() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */
        return sociedadmodel.listarSociedad();
    }

    public void obtenerSociedad() {
        int carnet = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));
        this.sociedad = sociedadmodel.obtenerSociedad(carnet);

    }

    public String guardarUsuario() {

        if (sociedadmodel.insertarSociedad(sociedad) != 1) {
            sociedadmodel.modificarSociedad(sociedad);
            JsfUtil.setFlashMessage("exito", "Sociedad actualizado");
            return "sociedad?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Sociedad registrado exitosamente");
//Forzando la redirección en el cliente
            return "sociedad?faces-redirect=true";
        }
    }

    public String eliminarUsuarios() {
// Leyendo el parametro enviado desde la vista
        int carnet = Integer.parseInt(JsfUtil.getRequest().getParameter("codigo"));

        //System.out.println(carnet);
        if (sociedadmodel.eliminarSociedad(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Sociedad eliminad"
                    + "a exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este Sociedad");
        }
        return "sociedad?faces-redirect=true";
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        idgenerado = sociedad.getNombre().substring(0, 3).toUpperCase().concat(dia);
        //sociedad.setId(idgenerado);
    }

    /**
     * @return the sociedad
     */
    public SociedadEntity getSociedad() {
        return sociedad;
    }

    /**
     * @param sociedad the sociedad to set
     */
    public void setSociedad(SociedadEntity sociedad) {
        this.sociedad = sociedad;
    }

}
