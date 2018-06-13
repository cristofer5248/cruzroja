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
import sv.cruzroja.www.entities.ActividadesdetallesEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class ActividadesDetaModel {

    public List<ActividadesdetallesEntity> listarActividades() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("ActividadesdetallesEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<ActividadesdetallesEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List listarActividadesXproyecto(int codigo) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("ActividadesdetallesEntity.findAllxidactividad").setParameter("codigo", codigo);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            System.out.print("No paso nadaaaa que raro aqi en actividadesdetamodel no hay datos");
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public int insertarActividad(ActividadesdetallesEntity actividad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(actividad); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;

        } catch (Exception e) {
            System.out.printf("Error en actividadesDetallesModel");
            em.close();
            e.getMessage();
            return 0;
        }
    }

    public int actualizarActividad(ActividadesdetallesEntity actividades) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(actividades);
            tran.commit();
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public List<ActividadesdetallesEntity> obtenerActividades(String codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            //Recupero el objeto desde la BD a través del método find
            Query consulta = em.createNamedQuery("ActividadesdetallesEntity.findById").setParameter("codigo", codigo);
            consulta.getResultList();
            List<ActividadesdetallesEntity> lista = consulta.getResultList();
            em.close();
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }

    }

    public ActividadesdetallesEntity obtenerActividadporId(String codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            ActividadesdetallesEntity estudiante = em.find(ActividadesdetallesEntity.class, codigo);
            em.close();
            return estudiante;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public int eliminarActividadDet(String id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
            ActividadesdetallesEntity est = em.find(ActividadesdetallesEntity.class, id);

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
            e.printStackTrace();
            em.close();
            return 0;
        }
    }

}
