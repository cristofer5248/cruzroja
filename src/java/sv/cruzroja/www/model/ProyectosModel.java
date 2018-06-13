/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import sv.cruzroja.www.entities.ProyectosEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.utils.JpaUtil;
import java.util.List;

/**
 *
 * @author crist
 */
public class ProyectosModel {

    public List<ProyectosEntity> listarProyectos() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("ProyectosEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<ProyectosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int eliminarProyecto(String id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            ProyectosEntity est = em.find(ProyectosEntity.class, id);
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();

            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int insertarProyecto(ProyectosEntity proyecto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(proyecto); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return 0;
        }
    }

    public ProyectosEntity obtenerProyecto(String carnet) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            ProyectosEntity estudiante = em.find(ProyectosEntity.class, carnet);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int modificarProyecto(ProyectosEntity proyecto) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(proyecto); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
}
