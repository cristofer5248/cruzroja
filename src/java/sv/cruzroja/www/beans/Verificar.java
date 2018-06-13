/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import sv.cruzroja.www.beans.Login;




@ManagedBean(name="verificaruser")
@ViewScoped
/**
 *
 * @author crist
 */
public class Verificar implements PhaseListener {
    Login credenciales = new Login();
    
   /*FacesContext facesContext = FacesContext.getCurrentInstance();
    LoginManager loginManager
            = (LoginManager) facesContext.getApplication()
                    .createValueBinding("#{loginManager}").getValue(facesContext);*/
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext faceContext = event.getFacesContext();
        String currenpage = faceContext.getViewRoot().getViewId();
        boolean isLoginpage = (currenpage.lastIndexOf("index.xhtml") > -1) ? true : false;
        HttpSession sesion = (HttpSession) faceContext.getExternalContext().getSession(true);
         Object usuario = sesion.getAttribute("usuario");
         
         
        
       /* if (loginManager.getDatosUsuarios().getTiposusuario().getIdTipoUsuario()==1) {
            
        }*/
       
         if(!isLoginpage && usuario==null){
            NavigationHandler  nh = faceContext.getApplication().getNavigationHandler();
            nh.handleNavigation(faceContext, null, "/index.xhtml");
            
        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
       
    }

    @Override
    public PhaseId getPhaseId() {
       return  PhaseId.RESTORE_VIEW;
    }
    
}
