/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.cruzroja.www.entities.DatosbeneficiadosEntity;
import sv.cruzroja.www.entities.GeneroEntity;
import sv.cruzroja.www.model.BeneficiadoModel;
import sv.cruzroja.www.model.ProyectosBeneficiadomodel;
import sv.cruzroja.www.utils.JsfUtil;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class BeneficiadoBeans {

    BeneficiadoModel modelo = new BeneficiadoModel();
    ProyectosBeneficiadomodel modelo2 = new ProyectosBeneficiadomodel();
    ProyectosbeneficiadoBean beneficiado2;
    private DatosbeneficiadosEntity beneficiado;
    Calendar c1 = Calendar.getInstance();
    private String idgenerado;
    public String apellidos;

    public BeneficiadoBeans() {
        beneficiado = new DatosbeneficiadosEntity();
        beneficiado.setGenero(new GeneroEntity());

    }

    public DatosbeneficiadosEntity getBeneficiado() {
        return beneficiado;
    }

    public void setBeneficiado(DatosbeneficiadosEntity beneficiado) {
        this.beneficiado = beneficiado;
    }

    public List<DatosbeneficiadosEntity> getListaEstudiantes() {
        try {
            if (this.apellidos != null) {
                return modelo.buscarXapellidos(apellidos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date date;
        Calendar cal2 = Calendar.getInstance();
        String year = String.valueOf(cal2.get(Calendar.YEAR));
        int month = cal2.get(Calendar.MONTH);
        month = month - 2;
        String mesString = String.valueOf(month);
        String fechamandar = year + "-" + mesString + "-" + "1";
        System.out.print(fechamandar);
        return modelo.listarLugarX2meses(fechamandar);
    }

    public void obtenerBeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        this.beneficiado = modelo.obtenerBeneficiado(codigo);

    }

    public String guardarBeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("tipo");
        System.out.print("codigoooo " + codigo);
        if (modelo.insertarEstudiante(beneficiado) != 1) {
            modelo.modificarBeneficiado(beneficiado);
            JsfUtil.setFlashMessage("exito", "El dato del beneficiado fue actualizado");
            if (codigo == "T") {
                return "registroBeneficiadoTec?faces-redirect=true";
            }
            return "registroBeneficiado?faces-redirect=true";
        } else {
            JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
            //Forzando la redirección en el cliente
            if (codigo == "T") {
                return "registroBeneficiadoTec?faces-redirect=true";
            }
            return "registroBeneficiado?faces-redirect=true";
        }

    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        idgenerado = beneficiado.getNombres().substring(0, 3).toUpperCase().concat(dia);
        beneficiado.setIdusuario(idgenerado);
    }

    public String eliminarBeneficiado() {
// Leyendo el parametro enviado desde la vista
        String carnet = JsfUtil.getRequest().getParameter("codigo");
        //System.out.println(carnet);
        if (modelo.eliminarLugar(carnet) > 0) {
            JsfUtil.setFlashMessage("exito", "Beneficiado eliminad"
                    + "o exitosamente");
        } else {
            JsfUtil.setErrorMessage(null, "No se pudo borrar a este beneficiado");
        }
        return "registroBeneficiado?faces-redirect=true";
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
