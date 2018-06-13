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
import sv.cruzroja.www.entities.DepartamentoEntity;

/**
 *
 * @author crist
 */
public class DepartamentoModel {

    public List<DepartamentoEntity> listarDepartamento() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("DepartamentoEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<DepartamentoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<DepartamentoEntity> listarDepartamentoDefault() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("DepartamentoEntity.findByIddepartamento").setParameter("iddepartamento", 1);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<DepartamentoEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public DepartamentoEntity listarByIdDepartamento(int codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            DepartamentoEntity estudiante = em.find(DepartamentoEntity.class, codigo);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

}
