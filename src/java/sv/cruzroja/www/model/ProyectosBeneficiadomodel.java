/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.entities.BeneficiadosEntity;
import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class ProyectosBeneficiadomodel {

    public List<BeneficiadosEntity> listarBeneficiados() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findAll");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<BeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Map<String, ?>> listanativa1(String id) {       
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
//        String sql = "SELECT\n"
//                + "     datosbeneficiados.`nombres` AS datosbeneficiados_nombres,\n"
//                + "     datosbeneficiados.`apellidos` AS datosbeneficiados_apellidos,\n"
//                + "     datosbeneficiados.`edad` AS datosbeneficiados_edad,\n"
//                + "     lugar.`nombre` AS lugar_nombre,\n"
//                + "     proyectos.`nombre` AS proyectos_nombre,\n"
//                + "     departamento.`nombre` AS departamento_nombre,\n"
//                + "     usuarios.`nombres` AS usuarios_nombres,\n"
//                + "     genero.`nombre` AS genero_nombre\n"
//                + "FROM\n"
//                + "     `datosbeneficiados` datosbeneficiados INNER JOIN `beneficiados` beneficiados ON datosbeneficiados.`idusuario` = beneficiados.`idbeneficiado`\n"
//                + "     INNER JOIN `lugarproyecto` lugarproyecto ON beneficiados.`idproyecto` = lugarproyecto.`idl`\n"
//                + "     INNER JOIN `lugar` lugar ON lugarproyecto.`idlugar` = lugar.`idlugar`\n"
//                + "     INNER JOIN `proyectos` proyectos ON lugarproyecto.`idlp` = proyectos.`idproyecto`\n"
//                + "     INNER JOIN `usuarios` usuarios ON proyectos.`encargado` = usuarios.`idusuario`\n"
//                + "     INNER JOIN `genero` genero ON usuarios.`genero` = genero.`idg`\n"
//                + "     AND genero.`idg` = datosbeneficiados.`genero`\n"
//                + "     INNER JOIN `departamento` departamento ON lugar.`departamento` = departamento.`iddepartamento`WHERE\n"
//                + "     beneficiados.`idbeneficiado` = $P{codbene}";
//
//        Query q = em.createNativeQuery(sql);
//        List<Object[]> lista1 = q.getResultList();
        List<Map<String, ?>> result = new ArrayList<Map<String, ?>>();
        Query consulta = em.createNamedQuery("BeneficiadosEntity.findByBeneficiado").setParameter("idbene", id);
        List<BeneficiadosEntity> lista = consulta.getResultList();
        Date myDate = new Date();
        try {
            
        
        for (BeneficiadosEntity b : lista) {

            Map<String, Object> m = new HashMap<String, Object>();
            m.put("datosbeneficiados_nombres", b.getIdbeneficiado().getNombres());
            m.put("datosbeneficiados_apellidos", b.getIdbeneficiado().getApellidos());
            m.put("genero_nombre", b.getIdbeneficiado().getGenero().getNombre());
            m.put("datosbeneficiados_edad", b.getIdbeneficiado().getEdad());
            m.put("lugar_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlugar().getNombre());
            m.put("departamento_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlugar().getDepartamento().getNombre());
            m.put("usuarios_nombres", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getEncargado().getNombres());
            m.put("proyectos_nombre", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getNombre());
            m.put("actividad", b.getIdproyecto().getDetalleactividad().getTitulo());
            m.put("area", b.getIdproyecto().getLugarproyectoPadre().getIdlp().getCategoria().getNombre());
            String fechaNformat= new SimpleDateFormat("dd-MM-yyyy").format(b.getFecha());
            m.put("fecha",fechaNformat);
            result.add(m);
            System.out.print("dfnsdnfsnfisdnfAQUI"+id);

        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public List<BeneficiadosEntity> listarBeneficiadosbyidbene(String id) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByBeneficiado").setParameter("idbene", id);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<BeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int buscarXidbeneYactividad(String id, int actividad) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByIdbeneYactividad").setParameter("idusu", id).setParameter("idactividad", actividad);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<BeneficiadosEntity> lista = consulta.getResultList();
            if (lista.size() > 0) {
                return 1;
            }
            em.close();// Cerrando el EntityManager
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return 0;
        }
    }

    public List<BeneficiadosEntity> listarBeneficiadosbyidlugar(String id, int edad) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        int edad2 = 0;
        if (edad > 64) {
            edad2 = 100;
        } else if (edad > 65 && edad < 101) {
            edad2 = 65;
        } else if (edad > 44 && edad < 65) {
            edad2 = 63;
        } else if (edad > 17 && edad < 45) {
            edad2 = 44;
        } else if (edad < 18) {
            edad2 = 18;
        }

        try {
            Query consulta = em.createNamedQuery("BeneficiadosEntity.findByLugar").setParameter("idlugar", id).setParameter("limite", edad2);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<BeneficiadosEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            e.printStackTrace();
            return null;
        }
    }

    public int insertarBeneficiadoProyecto(BeneficiadosEntity estudiante) {
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
            e.printStackTrace();
            return 0;
        }
    }

    public int modificarBeneficiadoProyecto(BeneficiadosEntity estudiante) {
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

    public int eliminar(String id) {
        //System.out.println(id);
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
//Recuperando el objeto a eliminar
            BeneficiadosEntity est = em.find(BeneficiadosEntity.class, id);

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
            e.printStackTrace();
            em.close();
            return 0;
        }
    }

}
