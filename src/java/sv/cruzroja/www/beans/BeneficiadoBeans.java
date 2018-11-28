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
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class BeneficiadoBeans {

    BeneficiadoModel modelo = new BeneficiadoModel();
    ProyectosBeneficiadomodel modelo2 = new ProyectosBeneficiadomodel();
    ProyectosbeneficiadoBean beneficiado2;
    private DatosbeneficiadosEntity beneficiado;
    private List<DatosbeneficiadosEntity> beneficiadolist;
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

    public void listaEstudiantes() {
        try {
            if (this.apellidos != null) {
                this.beneficiadolist = modelo.buscarXapellidos(apellidos);
            } else {
                Date date;
                Calendar cal2 = Calendar.getInstance();
                String year = String.valueOf(cal2.get(Calendar.YEAR));
                int month = cal2.get(Calendar.MONTH);
                month = month - 2;
                String mesString = String.valueOf(month);
                String fechamandar = year + "-" + mesString + "-" + "1";
                System.out.print(fechamandar);
                this.beneficiadolist = modelo.listarLugarX2meses(fechamandar);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }

    public void obtenerBeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("codigo");
        this.beneficiado = modelo.obtenerBeneficiado(codigo);

    }

    public void guardarBeneficiado() {
        String codigo = JsfUtil.getRequest().getParameter("tipo");
        System.out.print("codigoooo " + codigo);
        if (modelo.insertarEstudiante(beneficiado) != 1) {
            modelo.modificarBeneficiado(beneficiado);
            JsfUtil.setFlashMessage("exito", "El dato del beneficiado fue actualizado");
            if (codigo == "T") {

            }

        } else {
            JsfUtil.setFlashMessage("exito", "Alumno registrado exitosamente");
            //Forzando la redirección en el cliente
            if (codigo == "T") {

            }

        }

    }

    public void genearid() {
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH));
        int dianumero = Integer.parseInt(dia);
        int mesnumero = Integer.parseInt(mes);
        if (dianumero < 10 && mesnumero < 10) {
            mes = "0" + String.valueOf(mesnumero);
        }

        idgenerado = beneficiado.getNombres().substring(0, 3).toUpperCase().concat(dia).concat(mes);

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

    /**
     * @return the beneficiadolist
     */
    public List<DatosbeneficiadosEntity> getBeneficiadolist() {
        return beneficiadolist;
    }

    /**
     * @param beneficiadolist the beneficiadolist to set
     */
    public void setBeneficiadolist(List<DatosbeneficiadosEntity> beneficiadolist) {
        this.beneficiadolist = beneficiadolist;
    }

}
