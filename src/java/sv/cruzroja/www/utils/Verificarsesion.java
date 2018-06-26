/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.utils;

/**
 *
 * @author Saplic 01
 */
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sv.cruzroja.www.entities.UsuariosEntity;
import sv.cruzroja.www.model.UsuariosModel;

@ManagedBean
@ViewScoped

public class Verificarsesion {

    private String mensaje;

    public void verificarsession() throws IOException {
        try {

            FacesContext context = FacesContext.getCurrentInstance();
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nivel");
//        UsuariosEntity usuario = new UsuariosEntity();
            String nombre = null;

            if (us == null) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            } else {
                System.out.println("Hasta aqui el estado es: " + us.getActivo().toString());
                if (us.getActivo() == false) {
                    nombre = us.getNombres();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Coordinador", nombre));
                    UsuariosModel model = new UsuariosModel();
                    UsuariosEntity cambiaractivo = new UsuariosEntity();
                    cambiaractivo.setIdusuario(us.getIdusuario());
                    cambiaractivo.setNombres(us.getNombres());
                    cambiaractivo.setPass(us.getPass());
                    cambiaractivo.setTipousuario(us.getTipousuario());
                    cambiaractivo.setGenero(us.getGenero());
                    cambiaractivo.setActivo(Boolean.TRUE);
                    model.modificarUsuarios(cambiaractivo);
                    us.setActivo(Boolean.TRUE);
                    this.setMensaje(us.getNombres());
                }

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            JsfUtil.setErrorMessage(null, "Error interno");
        }

    }

    public void verificarsession2() throws IOException {
        try {

            FacesContext context = FacesContext.getCurrentInstance();
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nivel2");
//        UsuariosEntity usuario = new UsuariosEntity();
            String nombre = null;

            if (us == null) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            } else {
                //System.out.println("Hasta aqui el estado es: " + us.getActivo().toString()+" en el metodo de veriicarusuario2");
                if (us.getActivo() == false) {
                    nombre = us.getNombres();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Tecnico", nombre));
                    UsuariosModel model = new UsuariosModel();
                    UsuariosEntity cambiaractivo = new UsuariosEntity();
                    cambiaractivo.setIdusuario(us.getIdusuario());
                    cambiaractivo.setNombres(us.getNombres());
                    cambiaractivo.setPass(us.getPass());
                    cambiaractivo.setTipousuario(us.getTipousuario());
                    cambiaractivo.setGenero(us.getGenero());
                    cambiaractivo.setActivo(Boolean.TRUE);
                    model.modificarUsuarios(cambiaractivo);
                    us.setActivo(Boolean.TRUE);
                    this.setMensaje(us.getNombres());
                }

            }

        } catch (Exception e) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            JsfUtil.setErrorMessage(null, "Error interno");
        }
    }

    public void verificarsession3() throws IOException {
        try {

            FacesContext context = FacesContext.getCurrentInstance();
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nivel3");
//        UsuariosEntity usuario = new UsuariosEntity();
            String nombre = null;

            if (us == null) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            } else {
                //System.out.println("Hasta aqui el estado es: " + us.getActivo().toString()+" en el metodo de veriicarusuario2");
                if (us.getActivo() == false) {
                    nombre = us.getNombres();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido Tecnico", nombre));
                    UsuariosModel model = new UsuariosModel();
                    UsuariosEntity cambiaractivo = new UsuariosEntity();
                    cambiaractivo.setIdusuario(us.getIdusuario());
                    cambiaractivo.setNombres(us.getNombres());
                    cambiaractivo.setPass(us.getPass());
                    cambiaractivo.setTipousuario(us.getTipousuario());
                    cambiaractivo.setGenero(us.getGenero());
                    cambiaractivo.setActivo(Boolean.TRUE);
                    model.modificarUsuarios(cambiaractivo);
                    us.setActivo(Boolean.TRUE);
                    this.setMensaje(us.getNombres());
                }

            }

        } catch (Exception e) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?error=true");
            JsfUtil.setErrorMessage(null, "Error interno");
        }
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        try {
            UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            return us.getNombres();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error interno ", "Al obtener session"));

        }
        return null;

    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
