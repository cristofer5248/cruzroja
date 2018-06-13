/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.utils.JpaUtil;
import sv.cruzroja.www.entities.GeneroEntity;

/**
 *
 * @author crist
 */
public class GeneroModel {

    public List<GeneroEntity> listarLugar() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("GeneroEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<GeneroEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public static GeneroEntity obtenerGenero(int idg) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            GeneroEntity estudiante = em.find(GeneroEntity.class, idg);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            e.getMessage();
            return null;
        }
    }
}
