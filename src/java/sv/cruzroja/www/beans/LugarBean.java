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
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
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

    private List<LugarEntity> lugarEntityList;
    LugarModel modelo = new LugarModel();
    MunicipioModel modelo2 = new MunicipioModel();
    DepartamentoModel modelo3 = new DepartamentoModel();
    private LugarEntity lugar;
    private List<MunicipioEntity> muni = new ArrayList<MunicipioEntity>();
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    public Double latitud;
    public Double longitud;
    private String centerGeoMap = "41.850033, -87.6500523";
    private MapModel geoModel;
    private MapModel revGeoModel;
    private String centerRevGeoMap = "41.850033, -87.6500523";
    private MapModel simpleModel;

    /**
     * Creates a new instance of LugarBean
     */
    public LugarBean() {
        lugar = new LugarEntity();
        lugar.setDepartamento(new DepartamentoEntity());
        lugar.setMunicipio(new MunicipioEntity());
        lugar.setCategoria(new LugarcategoriaEntity());
        this.setMuni(modelo2.obtenerMunicipio(modelo3.listarByIdDepartamento(1)));
        geoModel = new DefaultMapModel();
        revGeoModel = new DefaultMapModel();

    }

    public void init() {
        simpleModel = new DefaultMapModel();

        lugarEntityList = modelo.listarLugar();
        for (LugarEntity sit : lugarEntityList) {
            LatLng coord1 = new LatLng(Double.parseDouble(sit.getLatitud()), Double.parseDouble(sit.getLongitud()));
            simpleModel.addOverlay(new Marker(coord1, sit.getNombre()));
        }

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

                lugar.setCoordenadas("Latitud: " + getLatitud() + " Longitud: " + getLongitud());
                lugar.setLatitud(String.valueOf(getLatitud()));
                lugar.setLongitud(String.valueOf(getLongitud()));
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
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        int diaint = Integer.valueOf(dia);
        int mesint = Integer.valueOf(mes);
        if (diaint < 10 && mesint < 10) {
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
        idgenerado = lugar.getNombre().substring(0, 3).toUpperCase().concat(dia).concat(mes);
        lugar.setIdlugar(idgenerado);
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();

            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

    public void onReverseGeocode(ReverseGeocodeEvent event) {
        List<String> addresses = event.getAddresses();
        LatLng coord = event.getLatlng();

        if (addresses != null && !addresses.isEmpty()) {
            centerRevGeoMap = coord.getLat() + "," + coord.getLng();
            revGeoModel.addOverlay(new Marker(coord, addresses.get(0)));
        }
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
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public MapModel getRevGeoModel() {
        return revGeoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public String getCenterRevGeoMap() {
        return centerRevGeoMap;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }
}
