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
import sv.cruzroja.www.entities.UsuariosEntity;

/**
 *
 * @author crist
 */
public class UsuariosModel {

    public List<UsuariosEntity> listarUsuarios() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<UsuariosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<UsuariosEntity> listarEncargados() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findByencargados");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<UsuariosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public List<UsuariosEntity> listarUsuariosTecnico() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<UsuariosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public UsuariosEntity iniciarsession(String usuario, String pass) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findByuserpass").setParameter("idusuario", usuario).setParameter("pass", pass);
            List<UsuariosEntity> listado = consulta.getResultList();
            if (!listado.isEmpty()) {
                return listado.get(0);
            }
            if (listado.size() == 0) {
                System.out.println("No hay nada al buscar el usuario con nivel 2 DDDDD:");
            }
            em.close();

            return listado.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public UsuariosEntity verificarnivel(String usuario) {
        System.out.println("Entre al verificar nivel 1");
        int nivel = 1;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findByuserpassnivel").setParameter("idusuario", usuario).setParameter("nivel", nivel);
            List<UsuariosEntity> listado = consulta.getResultList();
            if (!listado.isEmpty()) {
                return listado.get(0);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public UsuariosEntity verificarnivel2(String usuario) {
        System.out.println("Entre al verificar nivel 2");
        int nivel = 2;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findByuserpassnivel").setParameter("idusuario", usuario).setParameter("nivel", nivel);
            List<UsuariosEntity> listado = consulta.getResultList();
            if (listado.size() == 0) {
                System.out.println("Supuestamente no encontro nadie con ese nivel 2 we.... :C");
                return listado.get(0);
            } else {
                System.out.println("entonces si encontro coincidencia con un usuario nivel 2 al verificar...");
                return listado.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsuariosEntity verificarnivel3(String usuario) {
        System.out.println("Entre al verificar nivel 2");
        int nivel = 3;
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("UsuariosEntity.findByuserpassnivel").setParameter("idusuario", usuario).setParameter("nivel", nivel);
            List<UsuariosEntity> listado = consulta.getResultList();
            if (listado.size() == 0) {
                
                return listado.get(0);
            } else {
                
                return listado.get(0);
            }
        } catch (Exception e) {
            
        }
        return null;
    }

    public UsuariosEntity obtenerUsuario(String carnet) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            UsuariosEntity estudiante = em.find(UsuariosEntity.class, carnet);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarUsuarios(UsuariosEntity estudiante) {
        estudiante.setActivo(Boolean.FALSE);
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

    public int modificarUsuarios(UsuariosEntity estudiante) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(estudiante); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return 0;
        }
    }

    public int eliminarUsuarios(String id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            UsuariosEntity est = em.find(UsuariosEntity.class, id);

            System.out.println(est);
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
