/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sv.cruzroja.www.entities.TipouEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class TipousuarioModel {

    public List<TipouEntity> listarUsuarios() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("TipouEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<TipouEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

}
