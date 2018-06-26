/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.ArrayList;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.ViewScoped;
import sv.cruzroja.www.utils.JsfUtil;
import sv.cruzroja.www.entities.DepartamentoEntity;
import sv.cruzroja.www.entities.LugarEntity;
import sv.cruzroja.www.entities.MunicipioEntity;
import sv.cruzroja.www.model.LugarModel;
import sv.cruzroja.www.model.DepartamentoModel;
import sv.cruzroja.www.model.MunicipioModel;
import sv.cruzroja.www.entities.LugarcategoriaEntity;

/**
 *
 * @author crist
 */
@ManagedBean
@ViewScoped
public class LugarBean {

    LugarModel modelo = new LugarModel();
    MunicipioModel modelo2 = new MunicipioModel();
    DepartamentoModel modelo3 = new DepartamentoModel();
    private LugarEntity lugar;
    private List<MunicipioEntity> muni = new ArrayList<MunicipioEntity>();
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    public int latitud;
    public int longitud;

    /**
     * Creates a new instance of LugarBean
     */
    public LugarBean() {
        lugar = new LugarEntity();
        lugar.setDepartamento(new DepartamentoEntity());
        lugar.setMunicipio(new MunicipioEntity());
        lugar.setCategoria(new LugarcategoriaEntity());
        this.setMuni(modelo2.obtenerMunicipio(modelo3.listarByIdDepartamento(1)));

    }

    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    public List<LugarEntity> getListaLugar() {
        /* Notese que se llama al método listarEstudiantes
para obtener la lista de objetos a partir de la bd */

        return modelo.listarLugar();
    }

    public void obtenerLugar() {
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        LugarModel modelo2 = new LugarModel();
        this.lugar = modelo2.obtenerLugar(carnet);

    }

    public void guardarLugar() {
        try {
            if (latitud != 0 && longitud != 0) {
                
                lugar.setCoordenadas("Latitud: "+getLatitud()+" Longitud: "+getLongitud());
            }

            if (modelo.insertarEstudiante(lugar) != 1) {
                modelo.modificarLugar(lugar);
                JsfUtil.setFlashMessage("exito", "Lugar actualizado");
//            return "registroLugar?faces-redirect=true";
            } else {
                JsfUtil.setFlashMessage("exito", "lugar registrado exitosamente");
//Forzando la redirección en el cliente
//            return "registroLugar?faces-redirect=true";
            }
        } catch (Exception e) {
        }
    }

    public String eliminarLugar() {
// Leyendo el parametro enviado desde la vista
        String carnet = JsfUtil.getRequest().getParameter("codigo");

        //System.out.println(carnet);
        if (modelo.eliminarLugar(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Lugar eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este alumno");
        }
        return "registroLugar?faces-redirect=true";
    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        idgenerado = lugar.getNombre().substring(0, 3).toUpperCase().concat(dia);
        lugar.setIdlugar(idgenerado);
    }

    public void handleValueChange() {
        muni = modelo2.obtenerMunicipio(lugar.getDepartamento());
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
     * @return the idgenerado
     */
    public String getIdgenerado() {
        return idgenerado;
    }

    /**
     * @param idgenerado the idgenerado to set
     */
    public void setIdgenerado(String idgenerado) {
        this.idgenerado = idgenerado;
    }

    /**
     * @return the latitud
     */
    public int getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

}
