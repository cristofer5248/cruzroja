/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.beans;

import java.util.List;
import sv.cruzroja.www.model.BeneficiadoModel;

/**
 *
 * @author cristofer5248
 */
public class pruebasqueryNativo {

    public static void main(String[] arg) {
        imprimir();

    }

    public static void imprimir() {
        try {

            System.out.print("Iniciando...");
            BeneficiadoModel modelo = new BeneficiadoModel();
            List<Object[]> lista;
            lista = modelo.obteneractividades("ingresar un id valido de proyecto");
//            System.out.print("... Total de beneficiados = " + lista.size());
            

            for (Object[] z : modelo.obteneractividadesdatos(lista,"dsad")) {
                for (int i = 0; i < lista.size()+1; i++) {
                    System.out.print(z[i] + "\n");
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

//    /**
//     * @return the modelo
//     */
//    public BeneficiadoModel getModelo() {
//        return modelo;
//    }
//
//    /**
//     * @param modelo the modelo to set
//     */
//    public void setModelo(BeneficiadoModel modelo) {
//        this.modelo = modelo;
//    }
}
