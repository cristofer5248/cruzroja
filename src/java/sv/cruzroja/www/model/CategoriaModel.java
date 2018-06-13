/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import sv.cruzroja.www.entities.CategoriaEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.utils.JpaUtil;
import java.util.List;

/**
 *
 * @author crist
 */
public class CategoriaModel {

    public List<CategoriaEntity> listarCategoria() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("CategoriaEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<CategoriaEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public CategoriaEntity obtenerCategoria(int carnet) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            CategoriaEntity estudiante = em.find(CategoriaEntity.class, carnet);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarCategoria(CategoriaEntity estudiante) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(estudiante); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();

            return 0;
        }
    }

    public int modificarCategoria(CategoriaEntity estudiante) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(estudiante); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }

    public int eliminarCategoria(int id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            CategoriaEntity est = em.find(CategoriaEntity.class, id);
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
}
