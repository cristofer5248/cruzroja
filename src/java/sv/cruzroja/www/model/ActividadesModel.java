/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.cruzroja.www.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.cruzroja.www.entities.Actividades;

import sv.cruzroja.www.utils.JpaUtil;

/**
 *
 * @author crist
 */
public class ActividadesModel {

    public List<Actividades> listarActividadesporDepartamento(int iddepa) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllporDepartamento").setParameter("depa", iddepa);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public Actividades obtenerActividades(int codigo) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            //Recupero el objeto desde la BD a través del método find
            Actividades estudiante = em.find(Actividades.class, codigo);
            em.close();
            return estudiante;
        } catch (Exception e) {
            em.close();
            return null;
        }

    }

    public List<Map<String, ?>> proyectosPDF(String id) {
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
        Query consulta = em.createNamedQuery("Actividades.findAllXiddetalle").setParameter("codigo", id);
        List<Actividades> lista = consulta.getResultList();
        for (Actividades b : lista) {

            Map<String, Object> m = new HashMap<String, Object>();
            m.put("actividades_idactividad", b.getIdactividad());
            m.put("lugarcategoria_nombre", b.getLugarproyectoPadre().getIdlp().getCategoria().getNombre());
            m.put("municipio_nombre", b.getLugarproyectoPadre().getIdlugar().getMunicipio().getNombre());
            m.put("lugar_nombre", b.getLugarproyectoPadre().getIdlugar().getNombre());
            m.put("departamento_nombre", b.getLugarproyectoPadre().getIdlugar().getDepartamento().getNombre());
            m.put("actividadesdetalles_titulo", b.getDetalleactividad().getTitulo());
            result.add(m);

        }
        return result;

    }

    public int insertarActividad(Actividades actividad) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(actividad); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;

        } catch (Exception e) {
            System.out.printf("Error en actividadesModel");
            em.close();
            e.getMessage();
            return 0;
        }
    }

    public int actualizarActividad(Actividades actividades) {
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

    public List<Actividades> listarActividades() {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllOrder");
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public boolean buscarRepetidoIdyPadre(String codigo, String padre) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllIddetalleyPadre").setParameter("codigo", codigo).setParameter("padre", padre);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager

            if (lista.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return false;
        }
    }

    public List<Actividades> listarActividadesXproyecto(String codigo) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllXproyecto").setParameter("codigo", codigo);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            System.out.print("Tamaño de la lista:  "+lista.size());
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Actividades> listarActividadesXidDetalle(String codigo) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllXidActividadDetalle").setParameter("codigo", codigo);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public List<Actividades> listarActividadesXid(int codigo) {
//Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllXid").setParameter("codigo", codigo);
//El método getResultList() de la clase Query permite obtener
// la lista de resultados de una consulta de selección
            List<Actividades> lista = consulta.getResultList();
            System.out.print("Aqui deberia imprimirse unos datos ---> " + lista.get(0).getDetalleactividad().getTitulo() + " <-- finaliza");
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            System.out.printf("Osea que da error esto en listaractividadesxId");
            e.printStackTrace();
            em.close();
            return null;
        }
    }

    public int eliminarActividad(String id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

        int filasBorradas = 0;
        try {
            Query consulta = em.createNamedQuery("Actividades.findAllXiddetalle").setParameter("codigo", id);
            List<Actividades> est = consulta.getResultList();

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
