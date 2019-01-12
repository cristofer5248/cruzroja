/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.entities;

/**
 *
 * @author cristofer5248
 */
public class ConsolidadoTable {

    private String rangoEdades;
    private int masculino;
    private int femenino;

    public ConsolidadoTable() {

    }

  /**
     * @return the rangoEdades
     */
    public String getRangoEdades() {
        return rangoEdades;
    }

    /**
     * @param rangoEdades the rangoEdades to set
     */
    public void setRangoEdades(String rangoEdades) {
        this.rangoEdades = rangoEdades;
    }

    /**
     * @return the masculino
     */
    public int getMasculino() {
        return masculino;
    }

    /**
     * @param masculino the masculino to set
     */
    public void setMasculino(int masculino) {
        this.masculino = masculino;
    }

    /**
     * @return the femenino
     */
    public int getFemenino() {
        return femenino;
    }

    /**
     * @param femenino the femenino to set
     */
    public void setFemenino(int femenino) {
        this.femenino = femenino;
    }

}
