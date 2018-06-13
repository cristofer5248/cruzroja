/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sv.cruzroja.www.entities.DepartamentoEntity;
import sv.cruzroja.www.entities.MunicipioEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class MunicipioModel {

    public List<MunicipioEntity> listarMunicipio() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("MunicipioEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<MunicipioEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<MunicipioEntity> obtenerMunicipio(DepartamentoEntity codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            
            Query consulta = em.createNamedQuery("MunicipioEntity.findByIddepartamento" ).setParameter("iddepa", codigo);
            List<MunicipioEntity> lista = consulta.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null;
        }
    }
    

}
