/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.primefaces.context.RequestContext;
import sv.cruzroja.www.model.UsuariosModel;
import sv.cruzroja.www.entities.UsuariosEntity;

/**
 *
 * @author crist
 */
@ManagedBean
@SessionScoped
public class Login extends UsuariosBean {

    private String usuario;
    private int tipo;
    private String pass;

    /**
     * Creates a new instance of login
     */
    public Login() {

    }

    public String iniciarsesion() {
        UsuariosModel model = new UsuariosModel();
        UsuariosEntity user;
        user = null;
        UsuariosEntity usernivel1;
        user = model.iniciarsession(usuario, pass);

        if (user != null) {
            if (user.getPass().equals(pass)) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
                usernivel1 = model.verificarnivel(usuario);
                if (usernivel1 != null) {
                    System.out.println("cabal no coincide un nivel 2 con un nivel 1 daaa");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nivel", usernivel1);
                } else {
                    System.out.println("Aqui casual pasando por los if para el nivel2");
                    usernivel1 = model.verificarnivel2(usuario);                    
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nivel2", usernivel1);
                    
                    return "principalTec";
                }

                return "principalAd";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no existe"));
            return null;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no existe"));
        return null;
    }

    public String logut() {
        UsuariosModel model = new UsuariosModel();
        UsuariosEntity us = (UsuariosEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        System.out.println(us);
        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setIdusuario(us.getIdusuario());
        usuario.setNombres(us.getNombres());
        usuario.setPass(us.getPass());
        usuario.setGenero(us.getGenero());
        usuario.setTipousuario(us.getTipousuario());
        usuario.setActivo(Boolean.FALSE);
        model.modificarUsuarios(usuario);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
        
        return "login?faces-redirect=true";

    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
