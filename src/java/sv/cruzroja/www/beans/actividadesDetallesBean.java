/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.entities.ActividadesdetallesEntity;
import sv.cruzroja.www.model.ActividadesDetaModel;
import sv.cruzroja.www.utils.JsfUtil;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class actividadesDetallesBean {

    private ActividadesdetallesEntity actividadesdetalles;
    ActividadesDetaModel modelo = new ActividadesDetaModel();
    Calendar c1 = Calendar.getInstance();
    private String param1;

    /**
     * Creates a new instance of actividadesDetallesBean
     */
    public actividadesDetallesBean() {
        actividadesdetalles = new ActividadesdetallesEntity();

    }

    public String guardar() {

        String codigo = JsfUtil.getRequest().getParameter("tipo");
        System.out.print("Me desesperooooo el tipo es: " + codigo);
        int bandera1 = modelo.insertarActividad(actividadesdetalles);
        if (bandera1 == 0) {
            modelo.actualizarActividad(actividadesdetalles);
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

    public void generarid() {
        String idgenerado;
        try {
            String milisegundos = Integer.toString(c1.get(Calendar.MILLISECOND));
            idgenerado = actividadesdetalles.getDetalle().substring(0, 2).toUpperCase().concat(actividadesdetalles.getTitulo().substring(0, 2).toUpperCase()).concat(milisegundos.substring(0, 2));
//            idgenerado = idgenerado.concat(actividades.getTitulo().substring(0, 2).toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
            String milisegundos = Integer.toString(c1.get(Calendar.MILLISECOND));
            System.out.println(milisegundos);
            idgenerado = actividadesdetalles.getTitulo().substring(0, 3).concat(milisegundos);
        }
        actividadesdetalles.setIdactividadesdetalles(idgenerado);
    }

    public void obtenerActividad() {
        String carnet = this.param1;
        System.out.print("ESTE ES EL CODIGO RECOGIDO PARA MODIFICAR " + carnet);
        this.actividadesdetalles = modelo.obtenerActividadporId(carnet);
    }

    public List<ActividadesdetallesEntity> listarActividadesDet() {
        return modelo.listarActividades();
    }

    /**
     * @return the actividadesdetalles
     */
    public ActividadesdetallesEntity getActividadesdetalles() {
        return actividadesdetalles;
    }

    /**
     * @param actividadesdetalles the actividadesdetalles to set
     */
    public void setActividadesdetalles(ActividadesdetallesEntity actividadesdetalles) {
        this.actividadesdetalles = actividadesdetalles;
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
