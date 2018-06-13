/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author crist
 */
@ManagedBean
@RequestScoped
public class WizardBean {

    private boolean skip;
    private String uno; 
    private String dos;

    /**
     * Creates a new instance of WizardBean
     */
    public WizardBean() {
    }

    public String onFlowProcess(FlowEvent event) {

        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * @return the uno
     */
    public String getUno() {
        return uno;
    }

    /**
     * @param uno the uno to set
     */
    public void setUno(String uno) {
        this.uno = uno;
    }

    /**
     * @return the dos
     */
    public String getDos() {
        return dos;
    }

    /**
     * @param dos the dos to set
     */
    public void setDos(String dos) {
        this.dos = dos;
    }
    
}
